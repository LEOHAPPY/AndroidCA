namespace WCFService
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("Book")]
    public partial class Book
    {
        public string title { get; set; }

        public string description { get; set; }

        public double? price { get; set; }

        [StringLength(50)]
        public string cg { get; set; }

        [StringLength(150)]
        public string image { get; set; }

        public int? quantity { get; set; }

        [StringLength(50)]
        public string id { get; set; }

        
    }
    
}
