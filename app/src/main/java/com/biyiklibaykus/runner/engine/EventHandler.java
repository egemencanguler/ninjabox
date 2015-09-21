package com.biyiklibaykus.runner.engine;

import android.view.MotionEvent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by egemen on 11.09.2015.
 */
public class EventHandler
{
    private Queue<MotionEvent> mEventQueue;


    public EventHandler() {
        mEventQueue = new LinkedList<>();
    }

    public void add(MotionEvent event)
    {
        synchronized (this)
        {
            mEventQueue.add(event);
        }

    }

    public MotionEvent poll()
    {
        synchronized (this)
        {
            return mEventQueue.poll();
        }

    }

    public boolean empty()
    {
        synchronized (this)
        {
            return mEventQueue.isEmpty();
        }

    }
}
