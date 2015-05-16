package com.campusconnect.neo4j.akka.facebook.util;

import akka.actor.Actor;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Field;

import static org.fest.reflect.util.Accessibles.setAccessible;
import static org.fest.reflect.util.Accessibles.setAccessibleIgnoringExceptions;


/**
 * Created by sn1 on 3/2/15.
 */
public class SpringUntypedActorFactory implements UntypedActorFactory {

    private final DependencyInjectionFactory dependencyInjectionFactory;

    private final ApplicationContext applicationContext;

    public SpringUntypedActorFactory(Class<?> actorClass, ApplicationContext applicationContext) {
        this.dependencyInjectionFactory = new DefaultUntypedActorFactory(actorClass);
        this.applicationContext = applicationContext;
    }

    @Override
    public Actor create() throws Exception {
        return dependencyInjectionFactory.createAndInject();
    }

    private interface DependencyInjectionFactory {
        UntypedActor createAndInject();
    }

    private abstract class AbstractUntypedActorFactory implements DependencyInjectionFactory {

        @Override
        public final UntypedActor createAndInject() {
            try {
                UntypedActor untypedActor = create();

                Class<?> aClass = getActorClass();
                for (Field field : aClass.getDeclaredFields()) {

                    if (field.getAnnotation(Autowired.class) != null) {
                        boolean accessible = field.isAccessible();
                        try {
                            setAccessible(field, true);
                            field.set(untypedActor, applicationContext.getBean(field.getType()));
                        } catch (IllegalAccessException e) {
                            throw new IllegalStateException("Unable to create actor instance", e);
                        } finally {
                            setAccessibleIgnoringExceptions(field, accessible);
                        }
                    }
                }
                return untypedActor;

            } catch (Exception e) {
                throw new IllegalStateException("Unable to create actor instance", e);
            }

        }

        protected abstract Class<?> getActorClass();

        protected abstract UntypedActor create() throws Exception;
    }

    private final class SpecificUntypedActorFactory extends AbstractUntypedActorFactory {

        private final UntypedActorFactory specificFactory;
        private volatile Class<?> actorClass;

        private SpecificUntypedActorFactory(UntypedActorFactory specificFactory) {
            this.specificFactory = specificFactory;
        }

        @Override
        protected Class<?> getActorClass() {
            return actorClass;
        }

        @Override
        protected UntypedActor create() throws Exception {
            UntypedActor untypedActor = (UntypedActor) specificFactory.create();
            actorClass = untypedActor.getClass();
            return untypedActor;
        }
    }

    private final class DefaultUntypedActorFactory extends AbstractUntypedActorFactory {
        private final Class<?> actorClass;

        public DefaultUntypedActorFactory(Class<?> actorClass) {
            this.actorClass = actorClass;
        }

        @Override
        protected Class<?> getActorClass() {
            return actorClass;
        }

        @Override
        protected UntypedActor create() throws InstantiationException, IllegalAccessException {
            return (UntypedActor) actorClass.newInstance();
        }
    }
}
