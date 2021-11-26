# VVS-Webserver-Project by Danci Ionut Cosmin

# Part 1
Legend:

✓ -> done

x -> not done

~ -> partially done

# Implementation:
- Webserver ✓
- Config - CLI ✓
- GUI x

# Tests classes implemented:
- ReadKeyTest ✓
- mainTest ~
- configTest ~
- guiTests x
- WebServerTest ~

Current code coverage: 40.7%
![Code_Coverage_Image](resources/images/current_code_coverage.PNG)
Status: need help.

Nu am reusit sa fac asa multe teste unitare deoarece nu stiu exact cum ar trebuii sa testez cazurile in care apar metode de multithreading/blocking din codul meu, poate ar trebuii refactorizat si as dorii feedback daca se poate. Multumesc!

# Part 2 - Updated 26/11/2021

Static Analysis - First Run (FindBugs).
![findbugs_1](resources/images/findbugs_1.PNG)

Static Analysis - After fixing bugs (FindBugs).
![findbugs_2](resources/images/findbugs_2.PNG)

Dynamic Analysis - First Run (Visual VM).
![visual_vm_1](resources/images/visual_vm_1.PNG)

Dynamic Analysis - Graph after: starting server -> maintain mode activated -> stop server (Visual VM).
![visual_vm_2](resources/images/visual_vm_2.PNG)

Conclusions after dynamic analysis: No memory leaks found, memory usage seems alright, number of threads seems high, same for cpu usage before starting server ( didn't found a fix yet ).

# Part 3 - Update (soon)