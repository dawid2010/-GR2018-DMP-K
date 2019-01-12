using HomeBudgetManagment.Api.DAL.Entities;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace HomeBudgetManagment.Api.DAL
{
    public class HomeBudgetDbContext : DbContext
    {
        public DbSet<Household> Households { get; set; }
        public DbSet<Income> Incomes { get; set; }
        public DbSet<IncomeType> IncomeTypes { get; set; }
        public DbSet<Outcome> Outcomes { get; set; }
        public DbSet<OutcomeType> OutcomeTypes { get; set; }
        public DbSet<Target> Targets { get; set;}
        public DbSet<User> Users { get; set; }
        public DbSet<UserCredentials> UserCredentials { get; set; }
        public DbSet<UserInfo> UserInfos { get; set; }

    }
}