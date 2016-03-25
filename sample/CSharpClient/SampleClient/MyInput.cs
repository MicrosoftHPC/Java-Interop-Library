using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.Serialization;
using Newtonsoft.Json;
using SoamService;
using System.IO;

namespace SampleClient
{
    public class MyInput : Message
    {
        public MyInput()
        {
        }

        //=========================================================================
        //  Accessors and Mutators
        //=========================================================================
        public bool isBoolean()
        {
            return m_boolean;
        }

        public void setBoolean(bool m_boolean)
        {
            this.m_boolean = m_boolean;
        }

        public int getInt()
        {
            return m_int;
        }

        public void setInt(int m_int)
        {
            this.m_int = m_int;
        }

        public long getLong()
        {
            return m_long;
        }

        public void setLong(long m_long)
        {
            this.m_long = m_long;
        }

        public float getFloat()
        {
            return m_float;
        }

        public void setFloat(float m_float)
        {
            this.m_float = m_float;
        }

        public double getDouble()
        {
            return m_double;
        }

        public void setDouble(double m_double)
        {
            this.m_double = m_double;
        }

        public String getString()
        {
            return m_string;
        }

        public void setString(String m_string)
        {
            this.m_string = m_string;
        }

        public byte[] getBytes()
        {
            return m_bytes;
        }

        public void setBytes(byte[] m_bytes)
        {
            this.m_bytes = m_bytes;
        }

        public System.DateTime getDate()
        {
            return m_date;
        }

        public void setDate(System.DateTime m_date)
        {
            this.m_date = m_date;
        }

        override public String ToString()
        {
            StringBuilder sb = new StringBuilder();

            sb.AppendFormat("bool = {0}, ", this.m_boolean);
            sb.AppendFormat("int = {0}, ", this.m_int);
            sb.AppendFormat("long = {0}, ", this.m_long);
            sb.AppendFormat("float = {0}, ", this.m_float);
            sb.AppendFormat("double = {0}, ", this.m_double);
            sb.AppendFormat("string = {0}, ", this.m_string);
            sb.AppendFormat("bytes size = {0}, ", (this.m_bytes == null) ? 0 : this.m_bytes.Length);
            sb.AppendFormat("date = {0}, ", this.m_date.ToString());

            return sb.ToString();
        }

        //=========================================================================
        //  Private Member Variables
        //=========================================================================
        [JsonProperty("m_boolean")]
        private bool m_boolean = false;
        [JsonProperty("m_int")]
        private int m_int = 123;
        [JsonProperty("m_long")]
        private long m_long = 123456L;
        [JsonProperty("m_float")]
        private float m_float = 123.456F;
        [JsonProperty("m_double")]
        private double m_double = 123.456789;
        [JsonProperty("m_string")]
        private String m_string = "This is a sample string from MyInput.";
        [JsonProperty("m_bytes")]
        private byte[] m_bytes = { 0x11, 0x22, 0x33, 0x44 };
        [JsonProperty("m_date")]
        private System.DateTime m_date = DateTime.Now;

        public override void onDeserialize(InputStream stream)
        {
            byte[] data = stream.ToArray();
            string json = Encoding.Default.GetString(data);
            MyInput my = JsonHelper.FromJson<MyInput>(json);

            this.m_boolean = my.isBoolean();
            this.m_int = my.getInt();
            this.m_long = my.getLong();
            this.m_float = my.getFloat();
            this.m_double = my.getDouble();
            this.m_string = my.getString();
            this.m_bytes = my.getBytes();
            this.m_date = my.getDate();
        }

        public override void onSerialize(OutputStream stream)
        {
            string json = JsonHelper.ToJson<MyInput>(this);
            byte[] data = Encoding.Default.GetBytes(json);
            stream.Write(data, 0, data.Length);
            stream.Flush();
        }

    }
}
