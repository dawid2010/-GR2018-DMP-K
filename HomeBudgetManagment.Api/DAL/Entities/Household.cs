using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HomeBudgetManagment.Api.DAL.Entities
{
    public class Household
    {
        public int Id { get; set; }
        public string Code { get; set; }
        public string Description { get; set; }
        public ICollection<User> Users { get; set; }



    }
}