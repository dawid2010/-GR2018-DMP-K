using AutoMapper;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeBudgetManagment.Api.Models.Interfaces
{
    public interface IMapperConfProvider
    {
        MapperConfiguration ProvideMapperConfiguration();

    }
}
