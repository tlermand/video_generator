Type STATE = {STATE_DATA, MOVEMENT, PREDECESSOR}
Type LIST = [SET OF STATES]
initial: type STATE
initial = {[orange, green, red], " ", null}
final: type STATE
final = {[red, orange, green], " ", null}
current: type STATE
current = null
open: type LIST
open = []
SET_INITIAL(initial)
SET_TARGET(final)
open.ADD(initial)
open = [{[orange, green, red], " ", null}]
current = open.REMOVE_FIRST()
current = {[orange, green, red], " ", null}
open = []
current is not final
current = {[orange, green, red], " ", null}
EXPLORE_NODE(current)
open.ADD(NEW_NODE)
open = [{[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}]
open.ADD(NEW_NODE)
open = [{[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}, {[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}]
current = open.REMOVE_FIRST()
current = {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}
open = [{[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}]
current is not final
current = {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}
EXPLORE_NODE(current)
open.ADD(NEW_NODE)
open = [{[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}, {[orange, green, red], "exchange orange for green", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}]
open.ADD(NEW_NODE)
open = [{[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}, {[orange, green, red], "exchange orange for green", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}, {[green, red, orange], "exchange orange for red", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}]
current = open.REMOVE_FIRST()
current = {[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}
open = [{[orange, green, red], "exchange orange for green", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}, {[green, red, orange], "exchange orange for red", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}]
current is not final
current = {[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}
EXPLORE_NODE(current)
open.ADD(NEW_NODE)
open = [{[orange, green, red], "exchange orange for green", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}, {[green, red, orange], "exchange orange for red", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}, {[red, orange, green], "exchange red for orange", {[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}}]
open.ADD(NEW_NODE)
open = [{[orange, green, red], "exchange orange for green", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}, {[green, red, orange], "exchange orange for red", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}, {[red, orange, green], "exchange red for orange", {[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}}, {[orange, green, red], "exchange red for green", {[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}}]
current = open.REMOVE_FIRST()
current = {[orange, green, red], "exchange orange for green", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}
open = [{[green, red, orange], "exchange orange for red", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}, {[red, orange, green], "exchange red for orange", {[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}}, {[orange, green, red], "exchange red for green", {[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}}]
current is not final
current = {[orange, green, red], "exchange orange for green", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}
EXPLORE_NODE(current)
open.ADD(NEW_NODE)
open = [{[green, red, orange], "exchange orange for red", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}, {[red, orange, green], "exchange red for orange", {[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}}, {[orange, green, red], "exchange red for green", {[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}}, {[green, orange, red], "exchange green for orange", {[orange, green, red], "exchange orange for green", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}}]
open.ADD(NEW_NODE)
open = [{[green, red, orange], "exchange orange for red", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}, {[red, orange, green], "exchange red for orange", {[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}}, {[orange, green, red], "exchange red for green", {[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}}, {[green, orange, red], "exchange green for orange", {[orange, green, red], "exchange orange for green", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}}, {[orange, red, green], "exchange green for red", {[orange, green, red], "exchange orange for green", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}}]
current = open.REMOVE_FIRST()
current = {[green, red, orange], "exchange orange for red", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}
open = [{[red, orange, green], "exchange red for orange", {[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}}, {[orange, green, red], "exchange red for green", {[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}}, {[green, orange, red], "exchange green for orange", {[orange, green, red], "exchange orange for green", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}}, {[orange, red, green], "exchange green for red", {[orange, green, red], "exchange orange for green", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}}]
current is not final
current = {[green, red, orange], "exchange orange for red", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}
EXPLORE_NODE(current)
open.ADD(NEW_NODE)
open = [{[red, orange, green], "exchange red for orange", {[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}}, {[orange, green, red], "exchange red for green", {[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}}, {[green, orange, red], "exchange green for orange", {[orange, green, red], "exchange orange for green", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}}, {[orange, red, green], "exchange green for red", {[orange, green, red], "exchange orange for green", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}}, {[red, green, orange], "exchange red for green", {[green, red, orange], "exchange orange for red", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}}]
open.ADD(NEW_NODE)
open = [{[red, orange, green], "exchange red for orange", {[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}}, {[orange, green, red], "exchange red for green", {[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}}, {[green, orange, red], "exchange green for orange", {[orange, green, red], "exchange orange for green", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}}, {[orange, red, green], "exchange green for red", {[orange, green, red], "exchange orange for green", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}}, {[red, green, orange], "exchange red for green", {[green, red, orange], "exchange orange for red", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}}, {[green, orange, red], "exchange red for orange", {[green, red, orange], "exchange orange for red", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}}]
current = open.REMOVE_FIRST()
current = {[red, orange, green], "exchange red for orange", {[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}}
open = [{[orange, green, red], "exchange red for green", {[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}}, {[green, orange, red], "exchange green for orange", {[orange, green, red], "exchange orange for green", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}}, {[orange, red, green], "exchange green for red", {[orange, green, red], "exchange orange for green", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}}, {[red, green, orange], "exchange red for green", {[green, red, orange], "exchange orange for red", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}}, {[green, orange, red], "exchange red for orange", {[green, red, orange], "exchange orange for red", {[green, orange, red], "exchange green for orange", {[orange, green, red], " ", null}}}}]
current is final
current = {[red, orange, green], "exchange red for orange", {[orange, red, green], "exchange green for red", {[orange, green, red], " ", null}}}
