#{{{ Marathon
require_fixture 'default'
#}}} Marathon

severity("normal")

def test

    with_window("VVS WebServer") {
        click("Start Server")
        assert_p("Not running", "Text", "Server Status: ")
        assert_p("Not running_4", "Text", "Running")
        assert_p("Server Address:", "Text", "Server Address: ")
        assert_p("Not running_2", "Text", "localhost:12345")
        assert_p("Server Listening Port:", "Text", "Server Listening Port: ")
        assert_p("Not running_3", "Text", "12345")
        click("Activate Maintenance Mode")
        assert_p("Not running_4", "Text", "Maintenance")
        assert_p("Start Server", "Enabled", "false")
        assert_p("Stop Server", "Enabled", "true")
        assert_p("Deactivate Maintenance Mode", "Text", "Deactivate Maintenance Mode")
        assert_p("Deactivate Maintenance Mode", "Enabled", "true")
        click("Deactivate Maintenance Mode")
        assert_p("Not running_4", "Text", "Running")
        assert_p("Activate Maintenance Mode", "Text", "Activate Maintenance Mode")
        click("Activate Maintenance Mode")
        click("Stop Server")
        assert_p("Start Server", "Enabled", "true")
        assert_p("Stop Server", "Enabled", "false")
        assert_p("Activate Maintenance Mode", "Text", "Activate Maintenance Mode")
        assert_p("Activate Maintenance Mode", "Enabled", "false")
        assert_p("Not running_4", "Text", "Stopped")
        assert_p("Not running_2", "Text", "Stopped")
        assert_p("Not running_3", "Text", "Stopped")
        window_closed("VVS WebServer")
    }

end
