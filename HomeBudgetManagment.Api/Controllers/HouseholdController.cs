using AutoMapper;
using HomeBudgetManagment.Api.DAL;
using HomeBudgetManagment.Api.Models.Implementations;
using HomeBudgetManagment.Api.Models.Interfaces;
using HomeBudgetManagment.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace HomeBudgetManagment.Api.Controllers
{
    public class HouseholdController : ApiController
    {
        IMapper mapper;
        IMapperConfProvider confProvider;
        public HouseholdController()
        {
            this.confProvider = new MapperConfProvider();
            var cfg = confProvider.ProvideMapperConfiguration();
            mapper = cfg.CreateMapper(); 
        }
        [HttpGet]
        public IHttpActionResult GetHousehold(int Id)
        {
            using (var context = new HomeBudgetDbContext())
            {
                var entity = context.Households.FirstOrDefault(x => x.Id == Id);
                if(entity == null)
                {
                    return NotFound();
                }
                else
                {
                    var dto = mapper.Map<HouseholdDTO>(entity);
                    return Json(dto);
                }
            }

        }
        [HttpGet]
        public IHttpActionResult GetHouseholds()
        {
            using (var context = new HomeBudgetDbContext())
            {
                var households = context.Households.ToList();
                var dto = mapper.Map<List<HouseholdDTO>>(households);
                return Json(dto);

            }
        }


    }
}
