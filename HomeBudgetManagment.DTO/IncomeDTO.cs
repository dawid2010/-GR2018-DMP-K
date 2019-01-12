using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeBudgetManagment.DTO
{
    public class IncomeDTO
    {
        public int Id { get; set; }
        public double IncomeValue { get; set; }

        public  IncomeTypeDTO IncomeType { get; set; }
        public  UserDTO User { get; set; }
    }
}
