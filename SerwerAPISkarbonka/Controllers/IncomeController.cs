using System;
using System.Collections.Generic;
using System.Data.Entity.Migrations;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using ClassLibrary;

namespace SerwerAPISkarbonka.Controllers
{
    public class IncomeController : ApiController
    {

        public IEnumerable<Income> Get()
        {
            using (sEntities entities = new sEntities())
            {

                entities.Configuration.ProxyCreationEnabled = false;
                return entities.Incomes.ToList();
            }
        }

        public Income Get(int id)
        {
            using (sEntities entities = new sEntities())
            {
                entities.Configuration.ProxyCreationEnabled = false;
                return entities.Incomes.FirstOrDefault(e => e.id == id);
            }
        }

    
        public void Post(decimal value, string name, int account_id)
        {
            using (sEntities entities = new sEntities())
            {
                entities.Configuration.ProxyCreationEnabled = false;
                Income o = new Income();
                o.value = value;
                o.name = name;
                o.account_id = account_id;
                o.date = DateTime.Now;
                entities.Incomes.Add(o);
                entities.SaveChanges();
            }
        }

        public void Post(decimal value, string name, int account_id, string data)
        {
            using (sEntities entities = new sEntities())
            {
                entities.Configuration.ProxyCreationEnabled = false;
                Income o = new Income();
                o.value = value;
                o.name = name;
                o.account_id = account_id;

                o.date = DateTime.ParseExact(data, "yyyy-MM-dd", CultureInfo.InvariantCulture);
                entities.Incomes.Add(o);
                entities.SaveChanges();
            }
        }

        public void Put(int id, decimal value, string name, int account_id)
        {
            using (sEntities entities = new sEntities())
            {
                entities.Configuration.ProxyCreationEnabled = false;
                Income o = entities.Incomes.FirstOrDefault(e => e.id == id);
                o.value = value;
                o.name = name;
                o.account_id = account_id;
                o.date = DateTime.Now;

                entities.Incomes.AddOrUpdate(o);
                entities.SaveChanges();


            }
        }

        public void Delete(int id)
        {
            using (sEntities entities = new sEntities())
            {
                entities.Configuration.ProxyCreationEnabled = false;
                Income o = entities.Incomes.FirstOrDefault(e => e.id == id);
                entities.Incomes.Remove(o);
                entities.SaveChanges();
            }
        }
    }

}
