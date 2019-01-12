using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeBudgetManagment.DTO
{
    public class UserDTO
    {
        public int Id { get; set; }

        public virtual UserCredentialsDTO UserCredentials { get; set; }
        public virtual UserInfoDTO UserInfo { get; set; }

        public virtual HouseholdDTO Household { get; set; }

        public virtual ICollection<IncomeDTO> Incomes { get; set; }
        public virtual ICollection<OutcomeDTO> Outcomes { get; set; }

    }
}
