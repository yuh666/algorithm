import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yuh on 2018/3/30.
 */
public class Main {
    static Pattern pattern = Pattern.compile(
            "\\w+?(\\d+)\\s*\\(\\s*(\\d\\d(\\d\\d)).*?\".+?(\\d+)\"\\),\\s"
    );

    public static void main(String[] args) {
        String src = "reserved_channel_1(1030 , \"预留渠道1\"),\t \n" +
                "reserved_channel_2(1031 , \"预留渠道2\"),\t \n" +
                "reserved_channel_3(1032 , \"预留渠道3\"),\t \n" +
                "reserved_channel_4(1033 , \"预留渠道4\"),\t \n" +
                "reserved_channel_5(1034 , \"预留渠道5\"),\t \n" +
                "reserved_channel_6(1035 , \"预留渠道6\"),\t \n" +
                "reserved_channel_7(1036 , \"预留渠道7\"),\t \n" +
                "reserved_channel_8(1037 , \"预留渠道8\"),\t \n" +
                "reserved_channel_9(1038 , \"预留渠道9\"),\t \n" +
                "reserved_channel_10(1039 ,\"预留渠道10\"),\t \n" +
                "reserved_channel_11(1040 ,\"预留渠道11\"),\t \n" +
                "reserved_channel_12(1041 ,\"预留渠道12\"),\t \n" +
                "reserved_channel_13(1042 ,\"预留渠道13\"),\t \n" +
                "reserved_channel_14(1043 ,\"预留渠道14\"),\t \n" +
                "reserved_channel_15(1044 ,\"预留渠道15\"),\t \n" +
                "reserved_channel_16(1045 ,\"预留渠道16\"),\t \n" +
                "reserved_channel_17(1046 ,\"预留渠道17\"),\t \n" +
                "reserved_channel_18(1047 ,\"预留渠道18\"),\t \n" +
                "reserved_channel_19(1048 ,\"预留渠道19\"),\t \n" +
                "reserved_channel_20(1049 ,\"预留渠道20\"),\t \n" +
                "reserved_channel_21(1050 ,\"预留渠道21\"),\t \n" +
                "reserved_channel_22(1051 ,\"预留渠道22\"),\t \n" +
                "reserved_channel_23(1052 ,\"预留渠道23\"),\t \n" +
                "reserved_channel_24(1053 ,\"预留渠道24\"),\t \n" +
                "reserved_channel_25(1054 ,\"预留渠道25\"),\t \n" +
                "reserved_channel_26(1055 ,\"预留渠道26\"),\t \n" +
                "reserved_channel_27(1056 ,\"预留渠道27\"),\t \n" +
                "reserved_channel_28(1057 ,\"预留渠道28\"),\t \n" +
                "reserved_channel_29(1058 ,\"预留渠道29\"),\t \n" +
                "reserved_channel_30(1059 ,\"预留渠道30\"),\t \n" +
                "reserved_channel_31(1060 ,\"预留渠道31\"),\t \n" +
                "reserved_channel_32(1061 ,\"预留渠道32\"),\t \n" +
                "reserved_channel_33(1062 ,\"预留渠道33\"),\t \n" +
                "reserved_channel_34(1063 ,\"预留渠道34\"),\t \n" +
                "reserved_channel_35(1064 ,\"预留渠道35\"),\t \n" +
                "reserved_channel_36(1065 ,\"预留渠道36\"),\t \n" +
                "reserved_channel_37(1066 ,\"预留渠道37\"),\t \n" +
                "reserved_channel_38(1067 ,\"预留渠道38\"),\t \n" +
                "reserved_channel_39(1068 ,\"预留渠道39\"),\t \n" +
                "reserved_channel_40(1069 ,\"预留渠道40\"),\t \n" +
                "reserved_channel_41(1070 ,\"预留渠道41\"),\t \n" +
                "reserved_channel_42(1071 ,\"预留渠道42\"),\t \n" +
                "reserved_channel_43(1072 ,\"预留渠道43\"),\t \n" +
                "reserved_channel_44(1073 ,\"预留渠道44\"),\t \n" +
                "reserved_channel_45(1074 ,\"预留渠道45\"),\t \n" +
                "reserved_channel_46(1075 ,\"预留渠道46\"),\t \n" +
                "reserved_channel_47(1076 ,\"预留渠道47\"),\t \n" +
                "reserved_channel_48(1077 ,\"预留渠道48\"),\t \n" +
                "reserved_channel_49(1078 ,\"预留渠道49\"),\t \n" +
                "reserved_channel_50(1079 ,\"预留渠道50\"),\t \n" +
                "reserved_channel_51(1080 ,\"预留渠道51\"),\t \n" +
                "reserved_channel_52(1081 ,\"预留渠道52\"),\t \n" +
                "reserved_channel_53(1082 ,\"预留渠道53\"),\t \n" +
                "reserved_channel_54(1083 ,\"预留渠道54\"),\t \n" +
                "reserved_channel_55(1084 ,\"预留渠道55\"),\t \n" +
                "reserved_channel_56(1085 ,\"预留渠道56\"),\t \n" +
                "reserved_channel_57(1086 ,\"预留渠道57\"),\t \n" +
                "reserved_channel_58(1087 ,\"预留渠道58\"),\t \n" +
                "reserved_channel_59(1088 ,\"预留渠道59\"),\t \n" +
                "reserved_channel_60(1089 ,\"预留渠道60\"),\t \n" +
                "reserved_channel_61(1090 ,\"预留渠道61\"),\t \n" +
                "     ";




        Matcher matcher = pattern.matcher(src);
        String s = matcher.replaceAll("reserved_channel_$4($2 ,\\\"预留渠道$4\\\"),");
        System.out.println(s);
    }
}
