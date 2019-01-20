using ClassLibrary;
using System.Collections.Generic;
using System.Data.Entity.Migrations;
using System.Linq;
using System.Web.Http;

namespace SerwerAPISkarbonka.Controllers
{
    public class LRController : ApiController
    {

        public User Get(string login, string password)
        {
            using (sEntities entities = new sEntities())
            {
                entities.Configuration.ProxyCreationEnabled = false;
                if (entities.Users.FirstOrDefault(e => e.email.Equals(login)) != null)
                {
                    User ek = entities.Users.FirstOrDefault(e => e.email.Equals(login));
                    if (ek.password.Equals(password)){
                        return entities.Users.FirstOrDefault(e => e.email.Equals(login));
                    }
                }
                return null;
            }
        }
    }
}
