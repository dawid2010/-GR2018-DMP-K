using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HomeBudgetManagment.Api.DAL.Entities
{
    public class UserCredentials
    {
        public int Id { get; set; }
        public string Login { get; set; }

        public string Password { get; set; }
        public int UserId { get; set; }
        public virtual User User { get; set; }


    }
}