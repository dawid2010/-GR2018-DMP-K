using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HomeBudgetManagment.Api.DAL.Entities
{
    public class UserCredentials
    {

        public string Login { get; set; }

        public string Password { get; set; }

        public virtual User UserInfo { get; set; }


    }
}