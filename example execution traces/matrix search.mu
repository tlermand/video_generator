Type STATE = {STATE_DATA, MOVEMENT, PREDECESSOR}
Type LIST = [SET OF STATES]
initial: type STATE
initial = {[0,1], "initial", null}
target: type STATE
target = {[3,3], " ", null}
current: type STATE
current = null
statequeue: type LIST
statequeue = []
SET_INITIAL(initial)
SET_TARGET(target)
current = statequeue.REMOVE_FIRST()
current = {[0,1], "initial", null}
statequeue = []
current is not target
current = {[0,1], "initial", null}
EXPLORE_NODE(current)
statequeue.ADD(NEW_NODE)
statequeue = [{[0,0], "move up", {[0,1], "initial", null}}]
statequeue.ADD(NEW_NODE)
statequeue = [{[0,0], "move up", {[0,1], "initial", null}}, {[0,2], "move down", {[0,1], "initial", null}}]
current = statequeue.REMOVE_FIRST()
current = {[0,0], "move up", {[0,1], "initial", null}}
statequeue = [{[0,2], "move down", {[0,1], "initial", null}}]
current is not target
current = {[0,0], "move up", {[0,1], "initial", null}}
EXPLORE_NODE(current)
statequeue.ADD(NEW_NODE)
statequeue = [{[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}, {[0,2], "move down", {[0,1], "initial", null}}]
current = statequeue.REMOVE_FIRST()
current = {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}
statequeue = [{[0,2], "move down", {[0,1], "initial", null}}]
current is not target
current = {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}
EXPLORE_NODE(current)
statequeue.ADD(NEW_NODE)
statequeue = [{[2,0], "move right", {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}}, {[0,2], "move down", {[0,1], "initial", null}}]
current = statequeue.REMOVE_FIRST()
current = {[2,0], "move right", {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}}
statequeue = [{[0,2], "move down", {[0,1], "initial", null}}]
current is not target
current = {[2,0], "move right", {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}}
EXPLORE_NODE(current)
statequeue.ADD(NEW_NODE)
statequeue = [{[3,0], "move right", {[2,0], "move right", {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}}}, {[0,2], "move down", {[0,1], "initial", null}}]
current = statequeue.REMOVE_FIRST()
current = {[3,0], "move right", {[2,0], "move right", {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}}}
statequeue = [{[0,2], "move down", {[0,1], "initial", null}}]
current is not target
current = {[3,0], "move right", {[2,0], "move right", {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}}}
EXPLORE_NODE(current)
statequeue.ADD(NEW_NODE)
statequeue = [{[3,1], "move down", {[3,0], "move right", {[2,0], "move right", {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}}}}, {[0,2], "move down", {[0,1], "initial", null}}]
current = statequeue.REMOVE_FIRST()
current = {[3,1], "move down", {[3,0], "move right", {[2,0], "move right", {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}}}}
statequeue = [{[0,2], "move down", {[0,1], "initial", null}}]
current is not target
current = {[3,1], "move down", {[3,0], "move right", {[2,0], "move right", {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}}}}
EXPLORE_NODE(current)
statequeue.ADD(NEW_NODE)
statequeue = [{[0,2], "move down", {[0,1], "initial", null}}, {[3,2], "move down", {[3,1], "move down", {[3,0], "move right", {[2,0], "move right", {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}}}}}]
current = statequeue.REMOVE_FIRST()
current = {[0,2], "move down", {[0,1], "initial", null}}
statequeue = [{[3,2], "move down", {[3,1], "move down", {[3,0], "move right", {[2,0], "move right", {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}}}}}]
current is not target
current = {[0,2], "move down", {[0,1], "initial", null}}
EXPLORE_NODE(current)
statequeue.ADD(NEW_NODE)
statequeue = [{[0,3], "move down", {[0,2], "move down", {[0,1], "initial", null}}}, {[3,2], "move down", {[3,1], "move down", {[3,0], "move right", {[2,0], "move right", {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}}}}}]
statequeue.ADD(NEW_NODE)
statequeue = [{[0,3], "move down", {[0,2], "move down", {[0,1], "initial", null}}}, {[3,2], "move down", {[3,1], "move down", {[3,0], "move right", {[2,0], "move right", {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}}}}}, {[1,2], "move right", {[0,2], "move down", {[0,1], "initial", null}}}]
current = statequeue.REMOVE_FIRST()
current = {[0,3], "move down", {[0,2], "move down", {[0,1], "initial", null}}}
statequeue = [{[1,2], "move right", {[0,2], "move down", {[0,1], "initial", null}}}, {[3,2], "move down", {[3,1], "move down", {[3,0], "move right", {[2,0], "move right", {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}}}}}]
current is not target
current = {[0,3], "move down", {[0,2], "move down", {[0,1], "initial", null}}}
EXPLORE_NODE(current)
current = statequeue.REMOVE_FIRST()
current = {[1,2], "move right", {[0,2], "move down", {[0,1], "initial", null}}}
statequeue = [{[3,2], "move down", {[3,1], "move down", {[3,0], "move right", {[2,0], "move right", {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}}}}}]
current is not target
current = {[1,2], "move right", {[0,2], "move down", {[0,1], "initial", null}}}
EXPLORE_NODE(current)
statequeue.ADD(NEW_NODE)
statequeue = [{[3,2], "move down", {[3,1], "move down", {[3,0], "move right", {[2,0], "move right", {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}}}}}, {[2,2], "move right", {[1,2], "move right", {[0,2], "move down", {[0,1], "initial", null}}}}]
current = statequeue.REMOVE_FIRST()
current = {[3,2], "move down", {[3,1], "move down", {[3,0], "move right", {[2,0], "move right", {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}}}}}
statequeue = [{[2,2], "move right", {[1,2], "move right", {[0,2], "move down", {[0,1], "initial", null}}}}]
current is not target
current = {[3,2], "move down", {[3,1], "move down", {[3,0], "move right", {[2,0], "move right", {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}}}}}
EXPLORE_NODE(current)
statequeue.ADD(NEW_NODE)
statequeue = [{[2,2], "move right", {[1,2], "move right", {[0,2], "move down", {[0,1], "initial", null}}}}, {[3,3], "move down", {[3,2], "move down", {[3,1], "move down", {[3,0], "move right", {[2,0], "move right", {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}}}}}}]
current = statequeue.REMOVE_FIRST()
current = {[2,2], "move right", {[1,2], "move right", {[0,2], "move down", {[0,1], "initial", null}}}}
statequeue = [{[3,3], "move down", {[3,2], "move down", {[3,1], "move down", {[3,0], "move right", {[2,0], "move right", {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}}}}}}]
current is not target
current = {[2,2], "move right", {[1,2], "move right", {[0,2], "move down", {[0,1], "initial", null}}}}
EXPLORE_NODE(current)
statequeue.ADD(NEW_NODE)
statequeue = [{[3,3], "move down", {[3,2], "move down", {[3,1], "move down", {[3,0], "move right", {[2,0], "move right", {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}}}}}}, {[2,3], "move down", {[2,2], "move right", {[1,2], "move right", {[0,2], "move down", {[0,1], "initial", null}}}}}]
current = statequeue.REMOVE_FIRST()
current = {[3,3], "move down", {[3,2], "move down", {[3,1], "move down", {[3,0], "move right", {[2,0], "move right", {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}}}}}}
statequeue = [{[2,3], "move down", {[2,2], "move right", {[1,2], "move right", {[0,2], "move down", {[0,1], "initial", null}}}}}]
current is target
current = {[3,3], "move down", {[3,2], "move down", {[3,1], "move down", {[3,0], "move right", {[2,0], "move right", {[1,0], "move right", {[0,0], "move up", {[0,1], "initial", null}}}}}}}}
