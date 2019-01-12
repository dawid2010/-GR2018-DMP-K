using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace HomeBudgetManagment.Api.DAL.Entities
{
    public class User
    {
        public int Id { get; set; }

        public int UserCredentialsId { get; set; }
        [Required]
        public virtual UserCredentials UserCredentials { get; set; }


        public int UserInfoId { get; set; }
        [Required]
        public virtual UserInfo UserInfo { get; set; }

        public int? HouseholdId { get; set; }
        public virtual Household Household { get; set; }

        public virtual ICollection<Income> Incomes { get; set; }
        public virtual ICollection<Outcome> Outcomes { get; set; }





    }
}