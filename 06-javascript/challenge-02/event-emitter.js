const createEventEmitter = () => {
  const events = new Map();

  const on = (eventName, listener) => {
    const listeners = events.get(eventName) || [];
    listeners.push(listener);
    events.set(eventName, listeners);

    return () => {
      const updatedListeners = events
        .get(eventName)
        .filter((fn) => fn !== listener);
      events.set(eventName, updatedListeners);
    };
  };

  const once = (eventName, listener) => {
    const wrapper = (data) => {
      listener(data);
      off(eventName, wrapper);
    };
    on(eventName, wrapper);
  };

  const emit = (eventName, data) => {
    const listeners = events.get(eventName);
    if (!listeners) return;

    [...listeners].forEach((listener) => listener(data));
  };

  const off = (eventName, listener) => {
    if (!events.has(eventName)) return;

    if (!listener) {
      events.delete(eventName);
      return;
    }

    const updatedListeners = events
      .get(eventName)
      .filter((fn) => fn !== listener);

    updatedListeners.length
      ? events.set(eventName, updatedListeners)
      : events.delete(eventName);
  };

  return { on, once, emit, off };
};
