using ClassLibrary;
using System;
using System.Collections.Generic;
using System.Data.Entity.Migrations;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace SerwerAPISkarbonka.Controllers
{
    public class OutcomeController : ApiController
    {

        public IEnumerable<Outcome> Get()
        {
            using (sEntities entities = new sEntities())
            {

                entities.Configuration.ProxyCreationEnabled = false;
                return entities.Outcomes.ToList();
            }
        }

        public Outcome Get(int id)
        {
            using (sEntities entities = new sEntities())
            {
                entities.Configuration.ProxyCreationEnabled = false;
                return entities.Outcomes.FirstOrDefault(e => e.id == id);
            }
        }

        public void Post(decimal value, string name, int account_id)
        {
            using (sEntities entities = new sEntities())
            {
                entities.Configuration.ProxyCreationEnabled = false;
                Outcome o = new Outcome();


                o.value = value;
                o.name = name;
                o.account_id = account_id;
                o.date = DateTime.Now;
                entities.Outcomes.Add(o);
                entities.SaveChanges();
            }
        }

        public void Post(decimal value, string name, int account_id, string data)
        {
            using (sEntities entities = new sEntities())
            {
                entities.Configuration.ProxyCreationEnabled = false;
                Outcome o = new Outcome();
                o.value = value;
                o.name = name;
                o.account_id = account_id;

                o.date = DateTime.ParseExact(data, "yyyy-MM-dd", CultureInfo.InvariantCulture);
                entities.Outcomes.Add(o);
                entities.SaveChanges();
            }
        }

        public void Put(int id, decimal value, string name, int account_id)
        {
            using (sEntities entities = new sEntities())
            {
                entities.Configuration.ProxyCreationEnabled = false;
                Outcome o = entities.Outcomes.FirstOrDefault(e => e.id == id);
                o.value = value;
                o.name = name;
                o.account_id = account_id;
                o.date = DateTime.Now;

                entities.Outcomes.AddOrUpdate(o);
                entities.SaveChanges();


            }
        }

        public void Delete(int id)
        {
            using (sEntities entities = new sEntities())
            {
                entities.Configuration.ProxyCreationEnabled = false;
                Outcome o = entities.Outcomes.FirstOrDefault(e => e.id == id);
                entities.Outcomes.Remove(o);
                entities.SaveChanges();
            }
        }
    }
}
