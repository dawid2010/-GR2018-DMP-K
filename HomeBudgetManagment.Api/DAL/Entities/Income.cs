using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HomeBudgetManagment.Api.DAL.Entities
{
    public class Income
    {
        public int Id { get; set; }
        public double IncomeValue { get; set; }

        public virtual IncomeType IncomeType { get; set; }
        public virtual User User { get; set; }
    }
}