#{{{ Marathon
require_fixture 'default'
#}}} Marathon

severity("normal")

def test

    with_window("VVS WebServer") {
        click("Start Server")
        click("Stop Server")
        assert_p("Not running", "Text", "Server Status: ")
        assert_p("Not running_4", "Text", "Stopped")
        assert_p("Server Address:", "Text", "Server Address: ")
        assert_p("Not running_2", "Text", "Stopped")
        assert_p("Server Listening Port:", "Text", "Server Listening Port: ")
        assert_p("Not running_3", "Text", "Stopped")
        click("Start Server")
        click("Activate Maintenance Mode")
        click("Deactivate Maintenance Mode")
        click("Activate Maintenance Mode")
        click("Stop Server")
        assert_p("Not running", "Text", "Server Status: ")
        assert_p("Not running_4", "Text", "Stopped")
        assert_p("Server Address:", "Text", "Server Address: ")
        assert_p("Not running_2", "Text", "Stopped")
        assert_p("Server Listening Port:", "Text", "Server Listening Port: ")
        assert_p("Not running_3", "Text", "Stopped")
        assert_p("Start Server", "Text", "Start Server")
        assert_p("Start Server", "Enabled", "true")
        assert_p("Stop Server", "Text", "Stop Server")
        assert_p("Stop Server", "Enabled", "false")
        assert_p("Activate Maintenance Mode", "Text", "Activate Maintenance Mode")
        assert_p("Activate Maintenance Mode", "Enabled", "false")
        assert_p("Server Listening On Port", "Text", "Server Listening On Port ")
        assert_p("textfield0", "Enabled", "true")
        assert_p("{name=textfield1, type=java.awt.TextField} ()", "Enabled", "true")
        assert_p("textfield2", "Enabled", "true")
        window_closed("VVS WebServer")
    }

end
