package vip.jayson.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeConfigs {
    public static Map<String, Integer> nodeType = new HashMap<>();
    public static Map<String, String> varType = new HashMap<>();
    public static Map<String, List<String>> zoneStructureNameAndId = new HashMap<>();
    public static Map<String, List<String>> segmentStructureNameAndId = new HashMap<>();
    public static Map<String, List<String>> blocStructureNameAndId = new HashMap<>();
    static {
        nodeType.put("File", 1);
        nodeType.put("Zone", 2);
        nodeType.put("Segement", 4);
        nodeType.put("Block", 8);
        zoneStructureNameAndId.put("IO", Arrays.asList("IOAREA", "00020001"));
        zoneStructureNameAndId.put("VAR", Arrays.asList("VARAREA", "00020002"));
        zoneStructureNameAndId.put("BOOL", Arrays.asList("LOGICAREA", "00020003"));
        zoneStructureNameAndId.put("AFZ", Arrays.asList("ADDFUNAREA", "00020006"));
        zoneStructureNameAndId.put("LK", Arrays.asList("LK", "00000000"));
        zoneStructureNameAndId.put("TIS", Arrays.asList("IPM_SWITCH_PID", "00000000"));
        segmentStructureNameAndId.put("OCSEG", Arrays.asList("OCSEG", "00040001"));
        segmentStructureNameAndId.put("MMISEG", Arrays.asList("MMISEG", "00040002"));
        segmentStructureNameAndId.put("TCC4SEG", Arrays.asList("TCC4SEG", "00040003"));
        segmentStructureNameAndId.put("RBC1SEG", Arrays.asList("RBC1SEG", "00040004"));
        segmentStructureNameAndId.put("RBC2SEG", Arrays.asList("RBC2SEG", "00040005"));
        segmentStructureNameAndId.put("RBC3SEG", Arrays.asList("RBC3SEG", "00040006"));
        segmentStructureNameAndId.put("CBI3SEG", Arrays.asList("CBI3SEG", "00040007"));
        segmentStructureNameAndId.put("KDACSEG", Arrays.asList("KDACSEG", "00040008"));
        segmentStructureNameAndId.put("RBC4SEG", Arrays.asList("RBC4SEG", "00040009"));
        segmentStructureNameAndId.put("FSFB2SEG", Arrays.asList("GENERALFSFB2SEG", "0004000A"));
        segmentStructureNameAndId.put("OLCSEG", Arrays.asList("OLCSEG", "0004000B"));
        segmentStructureNameAndId.put("CCFSFB2SEG", Arrays.asList("CCFSFB2SEG", "0004000C"));
        segmentStructureNameAndId.put("CCSACEMSEG", Arrays.asList("CCSACEMSEG", "0004000D"));
        segmentStructureNameAndId.put("HLCISEG", Arrays.asList("HLCISEG", "0004000E"));
        segmentStructureNameAndId.put("HLVOBCSEG", Arrays.asList("HLVOBCSEG", "0004000F"));
        segmentStructureNameAndId.put("GPVBOOLSEG", Arrays.asList("COMMONBOOLSEG", "00040101"));
        segmentStructureNameAndId.put("LPVBOOLSEG", Arrays.asList("LOCKBOOLSEG", "00040102"));
        segmentStructureNameAndId.put("GSVBOOLSEG", Arrays.asList("COMMONBOOLSEG", "00040101"));
        segmentStructureNameAndId.put("LSVBOOLSEG", Arrays.asList("LOCKBOOLSEG", "00040102"));
        segmentStructureNameAndId.put("TSVBOOLSEG", Arrays.asList("TIMEBOOLSEG", "00040103"));
        segmentStructureNameAndId.put("OCBOOLSEG", Arrays.asList("OCINPUTBOOLSEG", "00040104"));
        segmentStructureNameAndId.put("MMIBOOLSEG", Arrays.asList("MMIINPUTBOOLSEG", "00040105"));
        segmentStructureNameAndId.put("TCC4BOOLSEG", Arrays.asList("TCC4INPUTBOOLSEG", "00040106"));
        segmentStructureNameAndId.put("RBC1BOOLSEG", Arrays.asList("RBC1INPUTBOOLSEG", "00040107"));
        segmentStructureNameAndId.put("RBC2BOOLSEG", Arrays.asList("RBC2INPUTBOOLSEG", "00040108"));
        segmentStructureNameAndId.put("RBC3BOOLSEG", Arrays.asList("RBC3INPUTBOOLSEG", "00040109"));
        segmentStructureNameAndId.put("CBI3BOOLSEG", Arrays.asList("CBI3INPUTBOOLSEG", "0004010A"));
        segmentStructureNameAndId.put("KDACBOOLSEG", Arrays.asList("KDACINPUTBOOLSEG", "0004010B"));
        segmentStructureNameAndId.put("RBC4BOOLSEG", Arrays.asList("RBC4INPUTBOOLSEG", "0004010C"));
        segmentStructureNameAndId.put("FSFB2BOOLSEG", Arrays.asList("GENERALFSFB2INPUTBOOLSEG", "0004010D"));
        segmentStructureNameAndId.put("OLCBOOLSEG", Arrays.asList("OLCINPUTBOOLSEG", "0004010E"));
        segmentStructureNameAndId.put("CCFSFB2BOOLSEG", Arrays.asList("CCFSFB2INPUTBOOLSEG", "0004010F"));
        segmentStructureNameAndId.put("CCSACEMBOOLSEG", Arrays.asList("CCSACEMINPUTBOOLSEG", "00040110"));
        segmentStructureNameAndId.put("HLCIBOOLSEG", Arrays.asList("HLCIINPUTBOOLSEG", "00040111"));
        segmentStructureNameAndId.put("HLVOBCBOOLSEG", Arrays.asList("HLVOBCINPUTBOOLSEG", "00040112"));
        segmentStructureNameAndId.put("LKBOOLSEG", Arrays.asList("LKINPUTBOOLSEG", "01040101"));
        segmentStructureNameAndId.put("VTLSEG", Arrays.asList("VTLSEG", "00040201"));
        segmentStructureNameAndId.put("QCBJSEG", Arrays.asList("QCBJSEG", "00040301"));
        segmentStructureNameAndId.put("SWITCHSEG", Arrays.asList("SWITCHSEG", "00040302"));
        segmentStructureNameAndId.put("MMISWITCHSEG", Arrays.asList("MMISWITCHSEG", "00040303"));
        blocStructureNameAndId.put("OC", Arrays.asList("OC", "00000000"));
        blocStructureNameAndId.put("MMI", Arrays.asList("MMI", "00000000"));
        blocStructureNameAndId.put("TCC4", Arrays.asList("TCC4", "00000000"));
        blocStructureNameAndId.put("RBC1", Arrays.asList("RBC1", "00000000"));
        blocStructureNameAndId.put("RBC2", Arrays.asList("RBC2", "00000000"));
        blocStructureNameAndId.put("RBC3", Arrays.asList("RBC3", "00000000"));
        blocStructureNameAndId.put("CBI3", Arrays.asList("CBI3", "00000000"));
        blocStructureNameAndId.put("KDAC", Arrays.asList("KDAC", "00000000"));
        blocStructureNameAndId.put("RBC4", Arrays.asList("RBC4", "00000000"));
        blocStructureNameAndId.put("FSFB2", Arrays.asList("GENERALFSFB2", "00000000"));
        blocStructureNameAndId.put("OLC", Arrays.asList("OLC", "00000000"));
        blocStructureNameAndId.put("CCFSFB2", Arrays.asList("CCFSFB2", "00000000"));
        blocStructureNameAndId.put("CCSACEM", Arrays.asList("CCSACEM", "00000000"));
        blocStructureNameAndId.put("HLCI", Arrays.asList("HLCI", "00000000"));
        blocStructureNameAndId.put("HLVOBC", Arrays.asList("HLVOBC", "00000000"));
        blocStructureNameAndId.put("QCBJ", Arrays.asList("QCBJ", "00000000"));
        varType.put("GPV", "03000000");
        varType.put("LPV", "01000000");
        varType.put("GSV", "03000000");
        varType.put("LSV", "01000000");
        varType.put("TSV", "02000000");
        varType.put("OC", "10000000");
        varType.put("MMI", "11000000");
        varType.put("TCC4", "12000000");
        varType.put("RBC1", "13000000");
        varType.put("RBC2", "14000000");
        varType.put("RBC3", "15000000");
        varType.put("CBI3", "16000000");
        varType.put("KDAC", "17000000");
        varType.put("RBC4", "18000000");
        varType.put("FSFB2", "19000000");
        varType.put("OLC", "1A000000");
        varType.put("CCFSFB2", "1B000000");
        varType.put("CCSACEM", "1C000000");
        varType.put("HLCI", "1D000000");
        varType.put("HLVOBC", "1E000000");
        varType.put("LK", "70000000");
    }




}
