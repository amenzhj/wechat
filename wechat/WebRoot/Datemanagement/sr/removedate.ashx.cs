using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.SqlClient;
using System.Data;

namespace Datemanagement.sr
{
    /// <summary>
    /// removedate 的摘要说明
    /// </summary>
    public class removedate : IHttpHandler
    {

        public void ProcessRequest(HttpContext context)
        {
            string id = context.Request["id"];
            SqlConnection con = new SqlConnection(@"Data Source=.\SQL2005;Initial Catalog=DocumentManagement;Integrated Security=True");
            string dfsdf = "DELETE FROM  [Userdate]  where ID=" + id + "";
          
            SqlDataAdapter da = new SqlDataAdapter(dfsdf, con);
            DataSet ds = new DataSet();
            da.Fill(ds);
            con.Close();
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