using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeBudgetManagment.DTO
{
    public class OutcomeDTO
    {
        public int Id { get; set; }
        public virtual OutcomeTypeDTO OutcomeType { get; set; }
        public double OutcomeValue { get; set; }
        public virtual UserDTO User { get; set; }
    }
}
