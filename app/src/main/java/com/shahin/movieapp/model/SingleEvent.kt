package com.shahin.movieapp.model

import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class SingleEvent<out T>(private val content: T) {

    private val _hasBeenHandled = AtomicBoolean(false)

    val hasBeenHandled
        get() = _hasBeenHandled.get()

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? =
        if (consume())
            content
        else
            null

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content

    fun consume(): Boolean =
        _hasBeenHandled.compareAndSet(false, true)
}

/**
 * An [Observer] for [SingleEvent]s, simplifying the pattern of checking if the [SingleEvent]'s content has
 * already been handled.
 *
 * [onEventUnhandledContent] is *only* called if the [SingleEvent]'s contents has not been handled.
 */
class SingleEventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) :
    Observer<SingleEvent<T>> {
    override fun onChanged(event: SingleEvent<T>?) {
        event?.getContentIfNotHandled()?.let(onEventUnhandledContent)
    }
}