using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HomeBudgetManagment.Api.DAL.Entities
{
    public class Outcome
    {
        public int Id { get; set; }
        public virtual OutcomeType OutcomeType { get; set; }
        public double OutcomeValue { get; set; }
        public virtual User User { get; set; }
    }
}