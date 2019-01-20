using ClassLibrary;

using System.Collections.Generic;
using System.Data.Entity.Migrations;
using System.Linq;
using System.Web.Http;

namespace SerwerAPISkarbonka.Controllers
{
    public class SkarbonkaController : ApiController
    {
        

     public IEnumerable<User> Get()
        {
            using (sEntities entities = new sEntities())
            {

                entities.Configuration.ProxyCreationEnabled = false;
                return entities.Users.ToList();
            }
        }

        public User Get(int id)
        {
            using (sEntities entities = new sEntities())
            {
                entities.Configuration.ProxyCreationEnabled = false;
                return entities.Users.FirstOrDefault(e => e.id == id);
            }
        }

        public User Get(int id_konto, int dodatek)
        {
            using (sEntities entities = new sEntities())
            {
                entities.Configuration.ProxyCreationEnabled = false;
                return entities.Users.FirstOrDefault(e => e.account_id == id_konto);
            }
        }

       

        public void Post(string frist_name, string last_name, string password, string email, string kod)
        {
            using (sEntities entities = new sEntities())
            {
                entities.Configuration.ProxyCreationEnabled = false;
                User u = new User();
   
                u.first_name = frist_name;
                u.last_name = last_name;
                u.password = password;
                u.email = email;

                Account o = entities.Accounts.FirstOrDefault(e => e.kod.Equals(kod));
                
                u.account_id = o.id;
                u.type = "1";
                entities.Users.Add(u);
                entities.SaveChanges();
            }
        }


        public void Put(int id, string frist_name, string last_name, string password, string email, int account_id, string type)
        {
            using (sEntities entities = new sEntities())
            {
                entities.Configuration.ProxyCreationEnabled = false;
                User u = entities.Users.FirstOrDefault(e => e.id == id);
                u.first_name = frist_name;
                u.last_name = last_name;
                u.password = password;

                u.email = email;
                u.account_id = account_id;
                u.type = type;


                entities.Users.AddOrUpdate(u);
                entities.SaveChanges();


            }
        }

        public void Delete(int id)
        {
            using (sEntities entities = new sEntities())
            {
                entities.Configuration.ProxyCreationEnabled = false;
                User u = entities.Users.FirstOrDefault(e => e.id == id);
                entities.Users.Remove(u);
                entities.SaveChanges();
            }
        }
    }
}
