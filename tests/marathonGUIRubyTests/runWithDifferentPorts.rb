#{{{ Marathon
require_fixture 'default'
#}}} Marathon

severity("normal")

def test

    with_window("VVS WebServer") {
        click("textfield0", 1, 42, 7)
        keystroke("textfield0", "Backspace")
        keystroke("textfield0", "7")
        click("Start Server")
        assert_p("Server Listening On Port", "Text", "Server Listening On Port ")
        assert_p("textfield0", "Text", "12347")
        assert_p("Not running", "Text", "Server Status: ")
        assert_p("Not running_4", "Text", "Running")
        assert_p("Server Address:", "Text", "Server Address: ")
        assert_p("Not running_2", "Text", "localhost:12347")
        assert_p("Server Listening Port:", "Text", "Server Listening Port: ")
        assert_p("Not running_3", "Text", "12347")
        click("Stop Server")
        click("textfield0", 1, 44, 8)
        keystroke("textfield0", "Backspace")
        keystroke("textfield0", "Backspace")
        keystroke("textfield0", "2")
        keystroke("textfield0", "3")
        click("Start Server")
        assert_p("Not running", "Text", "Server Status: ")
        assert_p("Not running_4", "Text", "Running")
        assert_p("Server Address:", "Text", "Server Address: ")
        assert_p("Not running_2", "Text", "localhost:12323")
        assert_p("Server Listening Port:", "Text", "Server Listening Port: ")
        assert_p("Not running_3", "Text", "12323")
        window_closed("VVS WebServer")
    }

end
