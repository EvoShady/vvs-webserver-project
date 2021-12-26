# VVS-Webserver-Project by Danci Ionut Cosmin

# Part 1
Legend:

✓ -> done  
x -> not done  
~ -> partially done

# Implementation:
- Webserver ✓
- Config - CLI ✓
- GUI ✓

# Tests classes implemented:
- ReadKeyTest ✓
- mainTest ~
- configTest ~
- guiTests ~
- WebServerTest ~

Current code coverage: 40.7%
![Code_Coverage_Image](resources/images/current_code_coverage.PNG)

Reason: Should modify WebServer class to make it more testable.

# Part 2 - Updated 26/11/2021

Static Analysis - First Run (FindBugs).
![findbugs_1](resources/images/findbugs_1.PNG)

Static Analysis - After fixing bugs (FindBugs).
![findbugs_2](resources/images/findbugs_2.PNG)

Dynamic Analysis - First Run (Visual VM).
![visual_vm_1](resources/images/visual_vm_1.PNG)

Dynamic Analysis - Graph after: starting server -> maintain mode activated -> stop server (Visual VM).
![visual_vm_2](resources/images/visual_vm_2.PNG)

Conclusions after dynamic analysis: No memory leaks found, memory and cpu usage seems alright, number of threads seems fine.

# Part 3 - Updated 26/12/2021

 - Finished Implementing GUI
 - Finished 10 tests with Marathon for GUI

Default GUI:
![default_gui](resources/images/defaultGui.PNG)

Marathon Tests:
![marathonGuiTesting](resources/images/marathonGuiTesting.PNG)

Example of Marathon Test:
![marathonGuiTestExample](resources/images/guiTestExample.PNG)
