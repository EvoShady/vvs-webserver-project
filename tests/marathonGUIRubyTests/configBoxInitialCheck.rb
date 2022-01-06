#{{{ Marathon
require_fixture 'default'
#}}} Marathon

severity("normal")

def test
  
    with_window("VVS WebServer") {
        assert_p("textfield0", "Text", "12345")
        assert_p("textfield0", "Enabled", "true")
        assert_p("Server Web Root Directory", "Text", "Server Web Root Directory ")
        assert_p("{name=textfield1, type=java.awt.TextField} ()", "Enabled", "true")
        assert_p("Check Path", "Enabled", "true")
        assert_p("Server Maintenance Directory", "Text", "Server Maintenance Directory ")
        assert_p("textfield2", "Enabled", "true")
        assert_p("Check Path_2", "Enabled", "true")
        window_closed("VVS WebServer")
    }
end
