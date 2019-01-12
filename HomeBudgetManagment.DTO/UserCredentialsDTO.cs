using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeBudgetManagment.DTO
{
    public class UserCredentialsDTO
    {
        public string Login { get; set; }

        public string Password { get; set; }

        public virtual UserDTO User { get; set; }
    }
}
