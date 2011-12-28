//------------------------------------------------------------------------------
// <copyright file="DataErrorCode.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      Define the error code for data operations
// </summary>
//------------------------------------------------------------------------------
package functiontest;

public enum CommonDataTestActionId {
    Default(0), READ_AccessDenied(1), READ_AccessDenied_ReadBytes(2), No_Read_PerReq(
            3), Read_Raw_Bytes(4),No_Read_Raw_PerReq(5);

    private int code;

    private CommonDataTestActionId(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static CommonDataTestActionId getEnum(int code) {
        // @todo: if this turns out a bottleneck, we need a do a hash lookup
        for (CommonDataTestActionId c : CommonDataTestActionId.class
                .getEnumConstants()) {
            if (c.getCode() == code)
                return c;
        }

        return CommonDataTestActionId.Default;
    }
}
