package org.miklas.ggalaxy.core.event

class EventBus {

    static Map<EventType, List<Closure>> listeners = [:]

    static void register(EventType type, Closure listener) {
        listeners.computeIfAbsent(type, { new ArrayList<Closure>() }) << listener
    }

    static void event(EventType type, def data) {
        listeners.get(type)?.each { it(data) }
    }

    static void event(EventType type) {
        listeners.get(type)?.each { it() }
    }

}
