#{{{ Marathon
require_fixture 'default'
#}}} Marathon

severity("normal")

def test

    with_window("VVS WebServer") {
        click("Start Server")
        click("Activate Maintenance Mode")
        assert_p("Start Server", "Enabled", "false")
        assert_p("Stop Server", "Enabled", "true")
        assert_p("Deactivate Maintenance Mode", "Enabled", "true")
        assert_p("Deactivate Maintenance Mode", "Text", "Deactivate Maintenance Mode")
        assert_p("textfield0", "Enabled", "false")
        assert_p("{name=textfield1, type=java.awt.TextField} ()", "Enabled", "true")
        assert_p("textfield2", "Enabled", "false")
        click("Deactivate Maintenance Mode")
        assert_p("textfield2", "Enabled", "true")
        assert_p("{name=textfield1, type=java.awt.TextField} ()", "Enabled", "false")
        assert_p("textfield0", "Enabled", "false")
        window_closed("VVS WebServer")
    }

end
