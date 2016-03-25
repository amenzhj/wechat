using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Text;

namespace Datemanagement.sr
{
    /// <summary>
    /// Handler1 的摘要说明
    /// </summary>
    public class GetID : IHttpHandler
    {

        public void ProcessRequest(HttpContext context)
        {
            string d = System.Web.HttpUtility.UrlDecode(context.Request["title"], Encoding.UTF8);
            context.Response.Write(d);
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