package com.ketrika.ledgerview.utils;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * A utility class for executing background tasks.
 */
public final class TaskExecutor implements AutoCloseable {

  private final ExecutorService executor;

  /**
   * Constructs a {@code TaskExecutor} backed by virtual threads.
   */
  public TaskExecutor() {
    this.executor = Executors.newVirtualThreadPerTaskExecutor();
  }

  /**
   * Submits a {@link Runnable} task for execution.
   *
   * @param task the runnable task
   * @return a {@link Future} representing the pending completion
   */
  public Future<?> submit(Runnable task) {
    Objects.requireNonNull(task);
    return executor.submit(task);
  }

  /**
   * Submits a {@link Callable} task for execution.
   *
   * @param task the callable task
   * @param <V> result type
   * @return a {@link Future} representing the pending completion
   */
  public <V> Future<V> submit(Callable<V> task) {
    Objects.requireNonNull(task);
    return executor.submit(task);
  }

  /**
   * Submits a {@link Runnable} task with a predefined result.
   *
   * @param task the runnable task
   * @param result the result to return
   * @param <V> result type
   * @return a {@link Future} representing the pending completion
   */
  public <V> Future<V> submit(Runnable task, V result) {
    Objects.requireNonNull(task);
    return executor.submit(task, result);
  }

  @Override
  public void close() throws Exception {
    executor.shutdown();
  }

}
