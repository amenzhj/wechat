using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Text;
using System.Data.SqlClient;
using System.Data;

namespace Datemanagement.sr
{
    /// <summary>
    /// getallid 的摘要说明
    /// </summary>
    public class getallid : IHttpHandler
    {

        public void ProcessRequest(HttpContext context)
        {
            string title = System.Web.HttpUtility.UrlDecode(context.Request["title"], Encoding.UTF8);
            string fullname = System.Web.HttpUtility.UrlDecode(context.Request["fullname"], Encoding.UTF8);
            string description = System.Web.HttpUtility.UrlDecode(context.Request["description"], Encoding.UTF8);
            string confname = System.Web.HttpUtility.UrlDecode(context.Request["confname"], Encoding.UTF8);
            string start = System.Web.HttpUtility.UrlDecode(context.Request["start"], Encoding.UTF8);
            string confshortname = System.Web.HttpUtility.UrlDecode(context.Request["confshortname"], Encoding.UTF8);
            string end = System.Web.HttpUtility.UrlDecode(context.Request["end"], Encoding.UTF8);

            SqlConnection con = new SqlConnection(@"Data Source=.\SQL2005;Initial Catalog=DocumentManagement;Integrated Security=True");
            string dfsdf="INSERT INTO  [Userdate]([Title],[start],[end],[UserID],[fullname],[confname],[confshortname],[allDay],[topic],[description])VALUES('" + title + "','" + start + "','" + end + "',1,'" + fullname + "','" + confname + "','" + confshortname + "',0,'dasd','" + description + "');select SCOPE_IDENTITY() as id";
            SqlDataAdapter da = new SqlDataAdapter(dfsdf, con);
            DataSet ds = new DataSet();
            da.Fill(ds);
            con.Close();

            string id = ds.Tables[0].Rows[0]["id"].ToString();
            context.Response.Write(id);
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