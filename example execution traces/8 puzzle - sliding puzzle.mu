Type STATE = {STATE_DATA, MOVEMENT, PREDECESSOR}
Type LIST = [SET OF STATES]
initial: type STATE
initial = {h12348765, "initial", null}
target: type STATE
target = {3124h8765, " ", null}
current: type STATE
current = null
statequeue: type LIST
statequeue = []
SET_INITIAL(initial)
SET_TARGET(target)
initial is not target
initial = {h12348765, "initial", null}
statequeue.ADD(NEW_NODE)
statequeue = [{h12348765, "initial", null}]
current = statequeue.REMOVE_FIRST()
current = {h12348765, "initial", null}
statequeue = []
EXPLORE_NODE(current)
right is not target
right = {1h2348765, "move right", {h12348765, "initial", null}}
statequeue.ADD(NEW_NODE)
statequeue = [{1h2348765, "move right", {h12348765, "initial", null}}]
down is not target
down = {312h48765, "move down", {h12348765, "initial", null}}
statequeue.ADD(NEW_NODE)
statequeue = [{1h2348765, "move right", {h12348765, "initial", null}}, {312h48765, "move down", {h12348765, "initial", null}}]
current = statequeue.REMOVE_FIRST()
current = {1h2348765, "move right", {h12348765, "initial", null}}
statequeue = [{312h48765, "move down", {h12348765, "initial", null}}]
EXPLORE_NODE(current)
left is not target
left = {h12348765, "move left", {1h2348765, "move right", {h12348765, "initial", null}}}
right is not target
right = {12h348765, "move right", {1h2348765, "move right", {h12348765, "initial", null}}}
statequeue.ADD(NEW_NODE)
statequeue = [{312h48765, "move down", {h12348765, "initial", null}}, {12h348765, "move right", {1h2348765, "move right", {h12348765, "initial", null}}}]
down is not target
down = {1423h8765, "move down", {1h2348765, "move right", {h12348765, "initial", null}}}
statequeue.ADD(NEW_NODE)
statequeue = [{312h48765, "move down", {h12348765, "initial", null}}, {12h348765, "move right", {1h2348765, "move right", {h12348765, "initial", null}}}, {1423h8765, "move down", {1h2348765, "move right", {h12348765, "initial", null}}}]
current = statequeue.REMOVE_FIRST()
current = {312h48765, "move down", {h12348765, "initial", null}}
statequeue = [{12h348765, "move right", {1h2348765, "move right", {h12348765, "initial", null}}}, {1423h8765, "move down", {1h2348765, "move right", {h12348765, "initial", null}}}]
EXPLORE_NODE(current)
right is target
right = {3124h8765, "move right", {312h48765, "move down", {h12348765, "initial", null}}}
up is not target
up = {h12348765, "move up", {312h48765, "move down", {h12348765, "initial", null}}}
down is not target
down = {312748h65, "move down", {312h48765, "move down", {h12348765, "initial", null}}}
statequeue.ADD(NEW_NODE)
statequeue = [{12h348765, "move right", {1h2348765, "move right", {h12348765, "initial", null}}}, {1423h8765, "move down", {1h2348765, "move right", {h12348765, "initial", null}}}, {312748h65, "move down", {312h48765, "move down", {h12348765, "initial", null}}}]
