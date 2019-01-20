using System;
using System.Collections.Generic;
using System.Data.Entity.Migrations;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using ClassLibrary;

namespace SerwerAPISkarbonka.Controllers
{
    public class AccountController : ApiController
    {

        public IEnumerable<Account> Get()
        {
            using (sEntities entities = new sEntities())
            {

                entities.Configuration.ProxyCreationEnabled = false;
                return entities.Accounts.ToList();
            }
        }

        public Account Get(int id)
        {
            using (sEntities entities = new sEntities())
            {
                entities.Configuration.ProxyCreationEnabled = false;
                return entities.Accounts.FirstOrDefault(e => e.id == id);
            }
        }

        public void Post(string name, string kod)
        {
            using (sEntities entities = new sEntities())
            {
                entities.Configuration.ProxyCreationEnabled = false;
                Account o = new Account();

                o.name = name;
                o.kod = kod;
                entities.Accounts.Add(o);
                entities.SaveChanges();
            }
        }

        public void Put(int id, string name)
        {
            using (sEntities entities = new sEntities())
            {
                entities.Configuration.ProxyCreationEnabled = false;
                Account o = entities.Accounts.FirstOrDefault(e => e.id == id);
                o.name = name;
                entities.Accounts.AddOrUpdate(o);
                entities.SaveChanges();


            }
        }

        public void Delete(int id)
        {
            using (sEntities entities = new sEntities())
            {
                entities.Configuration.ProxyCreationEnabled = false;
                Account o = entities.Accounts.FirstOrDefault(e => e.id == id);
                entities.Accounts.Remove(o);
                entities.SaveChanges();
            }
        }
    }
}
