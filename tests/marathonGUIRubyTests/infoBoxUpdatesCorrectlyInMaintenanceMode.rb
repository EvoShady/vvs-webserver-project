#{{{ Marathon
require_fixture 'default'
#}}} Marathon

severity("normal")

def test

    with_window("VVS WebServer") {
        click("Start Server")
        click("Activate Maintenance Mode")
        assert_p("Not running", "Text", "Server Status: ")
        assert_p("Not running_4", "Text", "Maintenance")
        click("Deactivate Maintenance Mode")
        assert_p("Not running", "Text", "Server Status: ")
        assert_p("Not running_4", "Text", "Running")
        window_closed("VVS WebServer")
    }

end
