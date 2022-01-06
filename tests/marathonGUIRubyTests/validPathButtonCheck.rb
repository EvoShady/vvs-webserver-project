#{{{ Marathon
require_fixture 'default'
#}}} Marathon

severity("normal")

def test

    with_window("VVS WebServer") {
        assert_p("textfield0", "Text", "12345")
        assert_p("{name=textfield1, type=java.awt.TextField} ()", "Text", "C:\\AC CTI\\An IV Sem I\\Sem I\\SSC\\vvs-webserver-project\\src\\TestSite\\")
        assert_p("textfield2", "Text", "C:\\AC CTI\\An IV Sem I\\Sem I\\SSC\\vvs-webserver-project\\src\\TestSite\\")
        click("Check Path")

        with_window("Message") {
            assert_p("lbl:IS A VALID PATH.", "Text", "IS A VALID PATH.")
            click("OK")
        }

        click("Check Path_2")

        with_window("Message") {
            assert_p("lbl:IS A VALID PATH.", "Text", "IS A VALID PATH.")
            click("OK")
        }

        click("{name=textfield1, type=java.awt.TextField} ()", 1, 426, 5)
        keystroke("{name=textfield1, type=java.awt.TextField} ()", "s")
        click("Check Path")

        with_window("Message") {
            assert_p("lbl:IS NOT A VALID PATH.", "Text", "IS NOT A VALID PATH.")
            click("OK")
        }

        click("{name=textfield1, type=java.awt.TextField} ()", 1, 413, 4)
        keystroke("{name=textfield1, type=java.awt.TextField} ()", "Backspace")
        click("Check Path")

        with_window("Message") {
            assert_p("lbl:IS A VALID PATH.", "Text", "IS A VALID PATH.")
            click("OK")
        }

        window_closed("VVS WebServer")
    }

end
