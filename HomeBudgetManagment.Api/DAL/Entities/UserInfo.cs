using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HomeBudgetManagment.Api.DAL.Entities
{
    public class UserInfo
    {
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public virtual User User { get; set; }
    }
}