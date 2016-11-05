package grailsdemo.sec

class Permission {

   User user
   Role role

   String permission

   static constraints = {
      permission unique: 'user'
      user nullable: true
      role nullable: true
   }
}
