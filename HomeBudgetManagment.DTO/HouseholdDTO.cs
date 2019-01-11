using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeBudgetManagment.DTO
{
    public class HouseholdDTO
    {
        public int Id { get; set; }
        public string Code { get; set; }
        public string Description { get; set; }
        public ICollection<UserDTO> Users { get; set; }
    }
}
