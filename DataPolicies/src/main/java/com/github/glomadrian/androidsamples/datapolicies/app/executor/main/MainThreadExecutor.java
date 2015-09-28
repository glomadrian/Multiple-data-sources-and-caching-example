package com.github.glomadrian.androidsamples.datapolicies.app.executor.main;

import com.github.glomadrian.androidsamples.datapolicies.app.executor.Executor;

/**
 * Mark interface to be able to inject distinct MainThreadExecutor implementations
 *
 * @author Adrián García Lomas
 */
public interface MainThreadExecutor<T extends Runnable> extends Executor<T> {
}
