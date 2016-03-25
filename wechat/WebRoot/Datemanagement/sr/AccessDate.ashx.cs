using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.SqlClient;
using System.Data;
using System.Web.Script.Serialization;
using System.Text;

namespace Datemanagement.sr
{
    /// <summary>
    /// AccessDate 的摘要说明
    /// </summary>
    public class AccessDate : IHttpHandler
    {

        public void ProcessRequest(HttpContext context)
        {
            JavaScriptSerializer jss = new JavaScriptSerializer();
            //context.Response.ContentType = "text/plain";
            //context.Response.Write("Hello World");
            Dictionary<string, string> drow = new Dictionary<string, string>();
            List<Dictionary<string, object>> gas = new List<Dictionary<string, object>>();
            string start = System.Web.HttpUtility.UrlDecode(context.Request["start"], Encoding.UTF8);
      
            string end = System.Web.HttpUtility.UrlDecode(context.Request["end"], Encoding.UTF8);
            SqlConnection con = new SqlConnection(@"Data Source=.\SQL2005;Initial Catalog=DocumentManagement;Integrated Security=True");

            SqlDataAdapter da = new SqlDataAdapter("SELECT   ID, Title, start, [end], UserID, fullname, confname, confshortname, allDay, topic, [description]  FROM         Userdate where UserID=1  and '" + start + "'<start and start<'" + end + "'", con);
            DataSet ds = new DataSet();
            da.Fill(ds);
            con.Close();
            for (int i = 0; i < ds.Tables[0].Rows.Count; i++)
            {
                Dictionary<string, object> drow2 = new Dictionary<string, object>();

                drow2.Add("id", ds.Tables[0].Rows[i]["ID"].ToString());
                drow2.Add("title", ds.Tables[0].Rows[i]["Title"].ToString());
                drow2.Add("start", ds.Tables[0].Rows[i]["start"].ToString());
                drow2.Add("end", ds.Tables[0].Rows[i]["end"].ToString());
                drow2.Add("fullname", ds.Tables[0].Rows[i]["fullname"].ToString());
                drow2.Add("confname", ds.Tables[0].Rows[i]["confname"].ToString());
                drow2.Add("confshortname", ds.Tables[0].Rows[i]["confshortname"].ToString());
                drow2.Add("allDay", ds.Tables[0].Rows[i]["allDay"]);
                drow2.Add("topic", ds.Tables[0].Rows[i]["topic"].ToString());
                drow2.Add("description", ds.Tables[0].Rows[i]["description"].ToString());
                gas.Add(drow2);
            
            }
            context.Response.Write(jss.Serialize(gas));   
        }

        public bool IsReusable
        {
            get
            {
                return false;
            }
        }
    }
}