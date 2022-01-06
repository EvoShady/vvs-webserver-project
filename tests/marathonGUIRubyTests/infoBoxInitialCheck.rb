#{{{ Marathon
require_fixture 'default'
#}}} Marathon

severity("normal")

def test
  
    with_window("VVS WebServer") {
        assert_p("Not running", "Text", "Server Status: ")
        assert_p("Not running_4", "Text", "Not running")
        assert_p("Server Address:", "Text", "Server Address: ")
        assert_p("Not running_2", "Text", "Not running")
        assert_p("Server Listening Port:", "Text", "Server Listening Port: ")
        assert_p("Not running_3", "Text", "Not running")
        window_closed("VVS WebServer")
    }

end
