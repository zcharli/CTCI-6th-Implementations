package random;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The approach: manage n sized linked list where each node represents one second of time passing, where n is
 * the largest window size that we care about. Update the window on each method call exposed to the public
 * interface relative to the timestamp of the last update.
 *
 * Why a linked lists: this is a suitable data structure to represent a sliding window because the complexity
 * of removals and additions from front or end of the list is O(1).
 *
 * Trade offs: making a space vs time trade off on the choice of Linked List vs Array based collections. Linked
 * list uses more memory, due the cost of objects headers and memory padding, but it's faster to modify the
 * list since it doesn't need resize, if the array needs to. Although if we try to diminish the need to resize
 * an array, I would run a circular array with the size of my max window. However, in order to calculate the
 * index of a circular array requires the use mod (%) operator is which much slower than traversing a pointer
 * like the linked list. I believe if calls to increment are very high, then mod would be the bottleneck.
 *
 * How my code would be extended if calls to increment is:
 *  extremely large - the implementation is optimizing for this case, many requests that come in per second means
 *                    that elapsedSeconds is zero, and thus we don't need to modify out list often
 *  extremely bursty - I'm not sure if we need to change anything here, please let me know if I forget to consider
 *                     something
 *  parallel environments - need to protect our counters from atomic updates and ensure only one thread is modifying
 *                          our time window at one time.
 *
 * Assumptions:
 *  - Assume the we only have three time windows to track
 *  - The smallest time denomination is going to be one second
 *
 * @author cli
 * @since 7/12/18
 */
public class SlidingWindowCounter
{
    private final static long NANOS_IN_ONE_SECOND = 1_000_000_000;
    private final static int SECONDS_IN_HOUR = 3600;
    private final static int SECONDS_IN_MINUTE = 60;
    
    
    // Keep a cached number of counts in our sliding window
    private AtomicLong m_countersOneSecond = new AtomicLong();
    private AtomicLong m_countersOneMinute = new AtomicLong();
    private AtomicLong m_countersOneHour = new AtomicLong();
    
    // Represents our three break points of time windows. Each reference directly points to a node the linked list.
    private TimeNode m_windowLinkedListHead; // Also represent counts within the first second
    private TimeNode m_countsInOneMinute;
    private TimeNode m_countsInOneHour;
    
    private volatile long m_lastTimestamp = System.nanoTime();
    private volatile long m_nanosUsedInCurrentSecond = 0;
    
    public SlidingWindowCounter()
    {
        m_windowLinkedListHead = _initializeWindows();
    }
    
    /**
     * We will accumulate all increments that have been registered in each passing second within one {@link TimeNode}.
     * Each n passing second means we need to adjust our sliding window by n nodes.
     * Each node with a registered increments which passes through the boundaries of our window must decrement the
     * counter since less increments were registered within that time space.
     */
    public void increment()
    {
        _updateWindows();
        
        // Now apply the increments to all three of our fields.
        m_windowLinkedListHead.m_increments = m_countersOneSecond.incrementAndGet(); // Set the number of counts in this second
        m_countersOneMinute.incrementAndGet();
        m_countersOneHour.incrementAndGet();
    }
    
    public long numLastSecond()
    {
        _updateWindows();
        return m_countersOneSecond.get();
    }
    
    public long numLastMinute()
    {
        _updateWindows();
        return m_countersOneMinute.get();
    }
    
    public long numLastHour()
    {
        _updateWindows();
        return m_countersOneHour.get();
    }
    
    /**
     * Update the linked list with the current time window outlook
     */
    private synchronized void _updateWindows()
    {
        // Update at most the max window size
        final long elapsedSeconds = Math.min(SECONDS_IN_HOUR, _calculateElapsedSeconds());
        int secondsToAdjust = 0;
        
        while (++secondsToAdjust <= elapsedSeconds)
        {
            // Adjust the counters for the passing second
            m_countersOneSecond.addAndGet(-m_windowLinkedListHead.m_increments);
            final TimeNode newHead = new TimeNode(null, 0, m_windowLinkedListHead);
            m_windowLinkedListHead.prev = newHead;
            m_windowLinkedListHead = newHead;
        
            // Adjust the counters for passing minute
            m_countersOneMinute.addAndGet(-m_countsInOneMinute.m_increments);
            m_countsInOneMinute = m_countsInOneMinute.prev;
        
            // Adjust the counters for the passing hour
            m_countersOneHour.addAndGet(-m_countsInOneHour.m_increments);
            m_countsInOneHour = m_countsInOneHour.prev;
            m_countsInOneHour.next = null; // Kill off the tail
        }
    }
    
    /**
     * Returns the amount of second passed since the last update to the counters.
     *
     * @return long seconds
     */
    private long _calculateElapsedSeconds()
    {
        final long currentTime = System.nanoTime(); // Nanos is the true
        final long nanosElapsed = currentTime - m_lastTimestamp + m_nanosUsedInCurrentSecond;
        final long secondsElapsed = nanosElapsed / NANOS_IN_ONE_SECOND; // Will round down automatically
        
        if (secondsElapsed > 0)
        {
            // More than one second has passed so update stamp
            m_lastTimestamp = currentTime;
            
            // Consider some time that has bled into the next second (part lost during the round down)
            m_nanosUsedInCurrentSecond = nanosElapsed - (secondsElapsed * NANOS_IN_ONE_SECOND);
        }
        
        return secondsElapsed;
    }
    
    private TimeNode _initializeWindows()
    {
        final TimeNode head = new TimeNode(null, 0, null);
        
        TimeNode current = head;
        
        for (int i = 1; i <= SECONDS_IN_HOUR; ++i)
        {
            if (i == SECONDS_IN_MINUTE)
            {
                m_countsInOneMinute = current;
            }
            
            if (i == SECONDS_IN_HOUR)
            {
                m_countsInOneHour = current;
            }
            
            final TimeNode next = new TimeNode(current, 0, null);
            current.next = next;
            current = next;
        }
        
        return head;
    }
    
    private static class TimeNode {
        private long m_increments;
        private TimeNode next;
        private TimeNode prev;
    
        TimeNode(TimeNode prev, long element, TimeNode next) {
            this.m_increments = element;
            this.next = next;
            this.prev = prev;
        }
    }
    
    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        
        return sb.append("numLastSecond[")
                 .append(numLastSecond())
                 .append("],numLastMinute[")
                 .append(numLastMinute())
                 .append("],numLastHour[")
                 .append(numLastHour())
                 .append("]").toString();
    }
    
    public static void main(final String[] args)
    {
        // Increment just one second
        long onePercentNanos = 990_000_000;
        SlidingWindowCounter counter = new SlidingWindowCounter();
        int count = 0;
        long s = System.nanoTime();
        long e = System.nanoTime() - s;
        while (e <= onePercentNanos)
        {
            counter.increment();
            count++;
            e = System.nanoTime() - s;
        }
        long numLastSecond = counter.numLastSecond();
        long numLastMinute = counter.numLastMinute();
        long numLastHour = counter.numLastHour();
        boolean match = numLastSecond == numLastHour && numLastSecond == numLastMinute && count == numLastSecond;
        System.out.println("State: " + counter.toString());
        System.out.println(String.format("[%s] %.2f seconds elapsed, SlidingWindowCounter[%d] count[%d]",
                                         match ? "MATCH" : "FAIL",
                                         (double) e/NANOS_IN_ONE_SECOND,
                                         numLastSecond,
                                         count));
        
        
        // Increment first then wait over a second
        counter = new SlidingWindowCounter();
        counter.increment();
        s = System.nanoTime();
        e = System.nanoTime() - s;
        while (e < NANOS_IN_ONE_SECOND) // Artificial pause
        {
            e = System.nanoTime() - s;
        }
        numLastSecond = counter.numLastSecond();
        numLastMinute = counter.numLastMinute();
        numLastHour = counter.numLastHour();
        match = numLastSecond == 0 && numLastMinute == 1 && numLastHour == 1;
        System.out.println("State: " + counter.toString());
        System.out.println(String.format("[%s] after incrementing and pausing for 1 second, we check numMinutes and numHours (num seconds should have expired)",
                                         match ? "MATCH" : "FAIL"));
        
    
    
        // When one counter crosses over a time window: Increment over a second then increment in the current second
        counter = new SlidingWindowCounter();
        counter.increment();
        s = System.nanoTime();
        e = System.nanoTime() - s;
        while (e < NANOS_IN_ONE_SECOND) // Artificial pause
        {
            e = System.nanoTime() - s;
        }
        counter.increment();
        numLastSecond = counter.numLastSecond();
        numLastMinute = counter.numLastMinute();
        numLastHour = counter.numLastHour();
        match = numLastSecond == 1 && numLastMinute == 2 && numLastHour == 2;
        System.out.println("State: " + counter.toString());
        System.out.println(String.format("[%s] after incrementing and pausing for 1 second then incrementing again, we check numMinutes and numHours",
                                         match ? "MATCH" : "FAIL"));
        
        final List<String> testParams = Arrays.asList(args);
        if (!testParams.contains("m"))
        {
            System.out.println("Beginning to test SlidingWindowCounter with one minute, be back in one minute...");
            long fiveSeconds = onePercentNanos * 5;
            counter = new SlidingWindowCounter();
            count = 0;
            s = System.nanoTime();
            e = System.nanoTime() - s;
            while (e <= fiveSeconds)
            {
                counter.increment();
                count++;
                e = System.nanoTime() - s;
            }
            numLastSecond = counter.numLastSecond();
            numLastMinute = counter.numLastMinute();
            numLastHour = counter.numLastHour();
            match = numLastHour == numLastMinute && count == numLastMinute && numLastMinute > numLastSecond;
            System.out.println("State: " + counter.toString());
            System.out.println(String.format("[%s] %.2f seconds elapsed, SlidingWindowCounter[%d] count[%d]",
                                             match ? "MATCH" : "FAIL",
                                             (double) e/NANOS_IN_ONE_SECOND,
                                             numLastMinute,
                                             count));
            
            // Now test expiration of the first minute and second by waiting some more seconds
            long oneMinute = NANOS_IN_ONE_SECOND * 60;
            counter = new SlidingWindowCounter();
            counter.increment();
            count = 1;
            s = System.nanoTime();
            e = System.nanoTime() - s;
            while (e <= oneMinute) // wait one more minute
            {
                e = System.nanoTime() - s;
            }
            numLastSecond = counter.numLastSecond();
            numLastMinute = counter.numLastMinute();
            numLastHour = counter.numLastHour();
            match = numLastHour == count &&
                    0d == numLastMinute &&
                    0d == numLastSecond;
            System.out.println("State: " + counter.toString());
            System.out.println(String.format("[%s] %.2f seconds elapsed, incremented once and waited over one minute... SlidingWindowCounter[%d] count[%d]",
                                             match ? "MATCH" : "FAIL",
                                             (double) e/NANOS_IN_ONE_SECOND,
                                             numLastHour,
                                             count));
        }
        
        // Parameterized tests since these take a long time
        if (testParams.contains("h"))
        {
            System.out.println("Beginning one hour test after incrementing counter by 1 (it should expire). Seriously... this is going to take one hour. Grab a beer and lay back");
            long oneHour = NANOS_IN_ONE_SECOND * SECONDS_IN_HOUR;
            counter = new SlidingWindowCounter();
            counter.increment();
            count = 1;
            s = System.nanoTime();
            e = System.nanoTime() - s;
            while (e <= oneHour)
            {
                count++;
                e = System.nanoTime() - s;
            }
            numLastSecond = counter.numLastSecond();
            numLastMinute = counter.numLastMinute();
            numLastHour = counter.numLastHour();
            match = count == 0 && 0d == numLastMinute && 0d == numLastSecond;
            System.out.println("State: " + counter.toString());
            System.out.println(String.format("[%s] %.2f seconds elapsed, one hour is done, all nodes expired, SlidingWindowCounter[%d] count[%d]",
                                             match ? "MATCH" : "FAIL",
                                             (double) e / NANOS_IN_ONE_SECOND,
                                             numLastHour,
                                             count));
    
        }
    }
}
