#{{{ Marathon
require_fixture 'default'
#}}} Marathon

severity("normal")

def test

    with_window("VVS WebServer") {
        assert_p("Start Server", "Text", "Start Server")
        assert_p("Start Server", "Enabled", "true")
        assert_p("Stop Server", "Text", "Stop Server")
        assert_p("Stop Server", "Enabled", "false")
        assert_p("Activate Maintenance Mode", "Text", "Activate Maintenance Mode")
        assert_p("Activate Maintenance Mode", "Enabled", "false")
        window_closed("VVS WebServer")
    }

end
