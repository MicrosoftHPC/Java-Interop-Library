
package org.datacontract.schemas._2004._07.system;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConsoleKey.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ConsoleKey">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Backspace"/>
 *     &lt;enumeration value="Tab"/>
 *     &lt;enumeration value="Clear"/>
 *     &lt;enumeration value="Enter"/>
 *     &lt;enumeration value="Pause"/>
 *     &lt;enumeration value="Escape"/>
 *     &lt;enumeration value="Spacebar"/>
 *     &lt;enumeration value="PageUp"/>
 *     &lt;enumeration value="PageDown"/>
 *     &lt;enumeration value="End"/>
 *     &lt;enumeration value="Home"/>
 *     &lt;enumeration value="LeftArrow"/>
 *     &lt;enumeration value="UpArrow"/>
 *     &lt;enumeration value="RightArrow"/>
 *     &lt;enumeration value="DownArrow"/>
 *     &lt;enumeration value="Select"/>
 *     &lt;enumeration value="Print"/>
 *     &lt;enumeration value="Execute"/>
 *     &lt;enumeration value="PrintScreen"/>
 *     &lt;enumeration value="Insert"/>
 *     &lt;enumeration value="Delete"/>
 *     &lt;enumeration value="Help"/>
 *     &lt;enumeration value="D0"/>
 *     &lt;enumeration value="D1"/>
 *     &lt;enumeration value="D2"/>
 *     &lt;enumeration value="D3"/>
 *     &lt;enumeration value="D4"/>
 *     &lt;enumeration value="D5"/>
 *     &lt;enumeration value="D6"/>
 *     &lt;enumeration value="D7"/>
 *     &lt;enumeration value="D8"/>
 *     &lt;enumeration value="D9"/>
 *     &lt;enumeration value="A"/>
 *     &lt;enumeration value="B"/>
 *     &lt;enumeration value="C"/>
 *     &lt;enumeration value="D"/>
 *     &lt;enumeration value="E"/>
 *     &lt;enumeration value="F"/>
 *     &lt;enumeration value="G"/>
 *     &lt;enumeration value="H"/>
 *     &lt;enumeration value="I"/>
 *     &lt;enumeration value="J"/>
 *     &lt;enumeration value="K"/>
 *     &lt;enumeration value="L"/>
 *     &lt;enumeration value="M"/>
 *     &lt;enumeration value="N"/>
 *     &lt;enumeration value="O"/>
 *     &lt;enumeration value="P"/>
 *     &lt;enumeration value="Q"/>
 *     &lt;enumeration value="R"/>
 *     &lt;enumeration value="S"/>
 *     &lt;enumeration value="T"/>
 *     &lt;enumeration value="U"/>
 *     &lt;enumeration value="V"/>
 *     &lt;enumeration value="W"/>
 *     &lt;enumeration value="X"/>
 *     &lt;enumeration value="Y"/>
 *     &lt;enumeration value="Z"/>
 *     &lt;enumeration value="LeftWindows"/>
 *     &lt;enumeration value="RightWindows"/>
 *     &lt;enumeration value="Applications"/>
 *     &lt;enumeration value="Sleep"/>
 *     &lt;enumeration value="NumPad0"/>
 *     &lt;enumeration value="NumPad1"/>
 *     &lt;enumeration value="NumPad2"/>
 *     &lt;enumeration value="NumPad3"/>
 *     &lt;enumeration value="NumPad4"/>
 *     &lt;enumeration value="NumPad5"/>
 *     &lt;enumeration value="NumPad6"/>
 *     &lt;enumeration value="NumPad7"/>
 *     &lt;enumeration value="NumPad8"/>
 *     &lt;enumeration value="NumPad9"/>
 *     &lt;enumeration value="Multiply"/>
 *     &lt;enumeration value="Add"/>
 *     &lt;enumeration value="Separator"/>
 *     &lt;enumeration value="Subtract"/>
 *     &lt;enumeration value="Decimal"/>
 *     &lt;enumeration value="Divide"/>
 *     &lt;enumeration value="F1"/>
 *     &lt;enumeration value="F2"/>
 *     &lt;enumeration value="F3"/>
 *     &lt;enumeration value="F4"/>
 *     &lt;enumeration value="F5"/>
 *     &lt;enumeration value="F6"/>
 *     &lt;enumeration value="F7"/>
 *     &lt;enumeration value="F8"/>
 *     &lt;enumeration value="F9"/>
 *     &lt;enumeration value="F10"/>
 *     &lt;enumeration value="F11"/>
 *     &lt;enumeration value="F12"/>
 *     &lt;enumeration value="F13"/>
 *     &lt;enumeration value="F14"/>
 *     &lt;enumeration value="F15"/>
 *     &lt;enumeration value="F16"/>
 *     &lt;enumeration value="F17"/>
 *     &lt;enumeration value="F18"/>
 *     &lt;enumeration value="F19"/>
 *     &lt;enumeration value="F20"/>
 *     &lt;enumeration value="F21"/>
 *     &lt;enumeration value="F22"/>
 *     &lt;enumeration value="F23"/>
 *     &lt;enumeration value="F24"/>
 *     &lt;enumeration value="BrowserBack"/>
 *     &lt;enumeration value="BrowserForward"/>
 *     &lt;enumeration value="BrowserRefresh"/>
 *     &lt;enumeration value="BrowserStop"/>
 *     &lt;enumeration value="BrowserSearch"/>
 *     &lt;enumeration value="BrowserFavorites"/>
 *     &lt;enumeration value="BrowserHome"/>
 *     &lt;enumeration value="VolumeMute"/>
 *     &lt;enumeration value="VolumeDown"/>
 *     &lt;enumeration value="VolumeUp"/>
 *     &lt;enumeration value="MediaNext"/>
 *     &lt;enumeration value="MediaPrevious"/>
 *     &lt;enumeration value="MediaStop"/>
 *     &lt;enumeration value="MediaPlay"/>
 *     &lt;enumeration value="LaunchMail"/>
 *     &lt;enumeration value="LaunchMediaSelect"/>
 *     &lt;enumeration value="LaunchApp1"/>
 *     &lt;enumeration value="LaunchApp2"/>
 *     &lt;enumeration value="Oem1"/>
 *     &lt;enumeration value="OemPlus"/>
 *     &lt;enumeration value="OemComma"/>
 *     &lt;enumeration value="OemMinus"/>
 *     &lt;enumeration value="OemPeriod"/>
 *     &lt;enumeration value="Oem2"/>
 *     &lt;enumeration value="Oem3"/>
 *     &lt;enumeration value="Oem4"/>
 *     &lt;enumeration value="Oem5"/>
 *     &lt;enumeration value="Oem6"/>
 *     &lt;enumeration value="Oem7"/>
 *     &lt;enumeration value="Oem8"/>
 *     &lt;enumeration value="Oem102"/>
 *     &lt;enumeration value="Process"/>
 *     &lt;enumeration value="Packet"/>
 *     &lt;enumeration value="Attention"/>
 *     &lt;enumeration value="CrSel"/>
 *     &lt;enumeration value="ExSel"/>
 *     &lt;enumeration value="EraseEndOfFile"/>
 *     &lt;enumeration value="Play"/>
 *     &lt;enumeration value="Zoom"/>
 *     &lt;enumeration value="NoName"/>
 *     &lt;enumeration value="Pa1"/>
 *     &lt;enumeration value="OemClear"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ConsoleKey")
@XmlEnum
public enum ConsoleKey {

    @XmlEnumValue("Backspace")
    BACKSPACE("Backspace"),
    @XmlEnumValue("Tab")
    TAB("Tab"),
    @XmlEnumValue("Clear")
    CLEAR("Clear"),
    @XmlEnumValue("Enter")
    ENTER("Enter"),
    @XmlEnumValue("Pause")
    PAUSE("Pause"),
    @XmlEnumValue("Escape")
    ESCAPE("Escape"),
    @XmlEnumValue("Spacebar")
    SPACEBAR("Spacebar"),
    @XmlEnumValue("PageUp")
    PAGE_UP("PageUp"),
    @XmlEnumValue("PageDown")
    PAGE_DOWN("PageDown"),
    @XmlEnumValue("End")
    END("End"),
    @XmlEnumValue("Home")
    HOME("Home"),
    @XmlEnumValue("LeftArrow")
    LEFT_ARROW("LeftArrow"),
    @XmlEnumValue("UpArrow")
    UP_ARROW("UpArrow"),
    @XmlEnumValue("RightArrow")
    RIGHT_ARROW("RightArrow"),
    @XmlEnumValue("DownArrow")
    DOWN_ARROW("DownArrow"),
    @XmlEnumValue("Select")
    SELECT("Select"),
    @XmlEnumValue("Print")
    PRINT("Print"),
    @XmlEnumValue("Execute")
    EXECUTE("Execute"),
    @XmlEnumValue("PrintScreen")
    PRINT_SCREEN("PrintScreen"),
    @XmlEnumValue("Insert")
    INSERT("Insert"),
    @XmlEnumValue("Delete")
    DELETE("Delete"),
    @XmlEnumValue("Help")
    HELP("Help"),
    @XmlEnumValue("D0")
    D_0("D0"),
    @XmlEnumValue("D1")
    D_1("D1"),
    @XmlEnumValue("D2")
    D_2("D2"),
    @XmlEnumValue("D3")
    D_3("D3"),
    @XmlEnumValue("D4")
    D_4("D4"),
    @XmlEnumValue("D5")
    D_5("D5"),
    @XmlEnumValue("D6")
    D_6("D6"),
    @XmlEnumValue("D7")
    D_7("D7"),
    @XmlEnumValue("D8")
    D_8("D8"),
    @XmlEnumValue("D9")
    D_9("D9"),
    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E"),
    F("F"),
    G("G"),
    H("H"),
    I("I"),
    J("J"),
    K("K"),
    L("L"),
    M("M"),
    N("N"),
    O("O"),
    P("P"),
    Q("Q"),
    R("R"),
    S("S"),
    T("T"),
    U("U"),
    V("V"),
    W("W"),
    X("X"),
    Y("Y"),
    Z("Z"),
    @XmlEnumValue("LeftWindows")
    LEFT_WINDOWS("LeftWindows"),
    @XmlEnumValue("RightWindows")
    RIGHT_WINDOWS("RightWindows"),
    @XmlEnumValue("Applications")
    APPLICATIONS("Applications"),
    @XmlEnumValue("Sleep")
    SLEEP("Sleep"),
    @XmlEnumValue("NumPad0")
    NUM_PAD_0("NumPad0"),
    @XmlEnumValue("NumPad1")
    NUM_PAD_1("NumPad1"),
    @XmlEnumValue("NumPad2")
    NUM_PAD_2("NumPad2"),
    @XmlEnumValue("NumPad3")
    NUM_PAD_3("NumPad3"),
    @XmlEnumValue("NumPad4")
    NUM_PAD_4("NumPad4"),
    @XmlEnumValue("NumPad5")
    NUM_PAD_5("NumPad5"),
    @XmlEnumValue("NumPad6")
    NUM_PAD_6("NumPad6"),
    @XmlEnumValue("NumPad7")
    NUM_PAD_7("NumPad7"),
    @XmlEnumValue("NumPad8")
    NUM_PAD_8("NumPad8"),
    @XmlEnumValue("NumPad9")
    NUM_PAD_9("NumPad9"),
    @XmlEnumValue("Multiply")
    MULTIPLY("Multiply"),
    @XmlEnumValue("Add")
    ADD("Add"),
    @XmlEnumValue("Separator")
    SEPARATOR("Separator"),
    @XmlEnumValue("Subtract")
    SUBTRACT("Subtract"),
    @XmlEnumValue("Decimal")
    DECIMAL("Decimal"),
    @XmlEnumValue("Divide")
    DIVIDE("Divide"),
    @XmlEnumValue("F1")
    F_1("F1"),
    @XmlEnumValue("F2")
    F_2("F2"),
    @XmlEnumValue("F3")
    F_3("F3"),
    @XmlEnumValue("F4")
    F_4("F4"),
    @XmlEnumValue("F5")
    F_5("F5"),
    @XmlEnumValue("F6")
    F_6("F6"),
    @XmlEnumValue("F7")
    F_7("F7"),
    @XmlEnumValue("F8")
    F_8("F8"),
    @XmlEnumValue("F9")
    F_9("F9"),
    @XmlEnumValue("F10")
    F_10("F10"),
    @XmlEnumValue("F11")
    F_11("F11"),
    @XmlEnumValue("F12")
    F_12("F12"),
    @XmlEnumValue("F13")
    F_13("F13"),
    @XmlEnumValue("F14")
    F_14("F14"),
    @XmlEnumValue("F15")
    F_15("F15"),
    @XmlEnumValue("F16")
    F_16("F16"),
    @XmlEnumValue("F17")
    F_17("F17"),
    @XmlEnumValue("F18")
    F_18("F18"),
    @XmlEnumValue("F19")
    F_19("F19"),
    @XmlEnumValue("F20")
    F_20("F20"),
    @XmlEnumValue("F21")
    F_21("F21"),
    @XmlEnumValue("F22")
    F_22("F22"),
    @XmlEnumValue("F23")
    F_23("F23"),
    @XmlEnumValue("F24")
    F_24("F24"),
    @XmlEnumValue("BrowserBack")
    BROWSER_BACK("BrowserBack"),
    @XmlEnumValue("BrowserForward")
    BROWSER_FORWARD("BrowserForward"),
    @XmlEnumValue("BrowserRefresh")
    BROWSER_REFRESH("BrowserRefresh"),
    @XmlEnumValue("BrowserStop")
    BROWSER_STOP("BrowserStop"),
    @XmlEnumValue("BrowserSearch")
    BROWSER_SEARCH("BrowserSearch"),
    @XmlEnumValue("BrowserFavorites")
    BROWSER_FAVORITES("BrowserFavorites"),
    @XmlEnumValue("BrowserHome")
    BROWSER_HOME("BrowserHome"),
    @XmlEnumValue("VolumeMute")
    VOLUME_MUTE("VolumeMute"),
    @XmlEnumValue("VolumeDown")
    VOLUME_DOWN("VolumeDown"),
    @XmlEnumValue("VolumeUp")
    VOLUME_UP("VolumeUp"),
    @XmlEnumValue("MediaNext")
    MEDIA_NEXT("MediaNext"),
    @XmlEnumValue("MediaPrevious")
    MEDIA_PREVIOUS("MediaPrevious"),
    @XmlEnumValue("MediaStop")
    MEDIA_STOP("MediaStop"),
    @XmlEnumValue("MediaPlay")
    MEDIA_PLAY("MediaPlay"),
    @XmlEnumValue("LaunchMail")
    LAUNCH_MAIL("LaunchMail"),
    @XmlEnumValue("LaunchMediaSelect")
    LAUNCH_MEDIA_SELECT("LaunchMediaSelect"),
    @XmlEnumValue("LaunchApp1")
    LAUNCH_APP_1("LaunchApp1"),
    @XmlEnumValue("LaunchApp2")
    LAUNCH_APP_2("LaunchApp2"),
    @XmlEnumValue("Oem1")
    OEM_1("Oem1"),
    @XmlEnumValue("OemPlus")
    OEM_PLUS("OemPlus"),
    @XmlEnumValue("OemComma")
    OEM_COMMA("OemComma"),
    @XmlEnumValue("OemMinus")
    OEM_MINUS("OemMinus"),
    @XmlEnumValue("OemPeriod")
    OEM_PERIOD("OemPeriod"),
    @XmlEnumValue("Oem2")
    OEM_2("Oem2"),
    @XmlEnumValue("Oem3")
    OEM_3("Oem3"),
    @XmlEnumValue("Oem4")
    OEM_4("Oem4"),
    @XmlEnumValue("Oem5")
    OEM_5("Oem5"),
    @XmlEnumValue("Oem6")
    OEM_6("Oem6"),
    @XmlEnumValue("Oem7")
    OEM_7("Oem7"),
    @XmlEnumValue("Oem8")
    OEM_8("Oem8"),
    @XmlEnumValue("Oem102")
    OEM_102("Oem102"),
    @XmlEnumValue("Process")
    PROCESS("Process"),
    @XmlEnumValue("Packet")
    PACKET("Packet"),
    @XmlEnumValue("Attention")
    ATTENTION("Attention"),
    @XmlEnumValue("CrSel")
    CR_SEL("CrSel"),
    @XmlEnumValue("ExSel")
    EX_SEL("ExSel"),
    @XmlEnumValue("EraseEndOfFile")
    ERASE_END_OF_FILE("EraseEndOfFile"),
    @XmlEnumValue("Play")
    PLAY("Play"),
    @XmlEnumValue("Zoom")
    ZOOM("Zoom"),
    @XmlEnumValue("NoName")
    NO_NAME("NoName"),
    @XmlEnumValue("Pa1")
    PA_1("Pa1"),
    @XmlEnumValue("OemClear")
    OEM_CLEAR("OemClear");
    private final String value;

    ConsoleKey(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ConsoleKey fromValue(String v) {
        for (ConsoleKey c: ConsoleKey.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
