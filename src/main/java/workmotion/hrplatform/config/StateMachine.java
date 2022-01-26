package workmotion.hrplatform.config;

public enum StateMachine {

    ADDED {
        @Override
        public StateMachine nextState() {
            return IN_CHECK;
        }

    },
    IN_CHECK {
        @Override
        public StateMachine nextState() {
            return APPROVED;
        }

    },
    APPROVED {
        @Override
        public StateMachine nextState() {
            return ACTIVE;
        }

    }, ACTIVE {
        @Override
        public StateMachine nextState() {
            return this;
        }

    };

    public abstract StateMachine nextState();
}
